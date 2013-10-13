package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.DataTypes.MapCoord;
import net.thetabx.jmcgui.DataTypes.MapData;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class PChunkData extends MPWPacket {
    private final static short packetId = 0x33;
    // Last update 74

    // Server to client
    private int x;
    private int z;
    private Boolean groundUpContinuous;
    private int primaryBitMap; // UShort
    private int addBitMap; // UShort
    private int compressedSize;
    private byte[] compressedData; // UByte array

    public PChunkData(TCPReader in) throws Exception {
        super(packetId);
        x = in.readInt();
        z = in.readInt();
        groundUpContinuous = in.readBool();
        primaryBitMap = in.readUShort();
        addBitMap = in.readUShort();
        compressedSize = in.readInt();
        compressedData = in.readByteArray(compressedSize);
        // TODO Check Byte/UByte array
    }

    public int getAddBitMap() {
        return addBitMap;
    }

    public Boolean getGroundUpContinuous() {
        return groundUpContinuous;
    }

    public int getPrimaryBitMap() {
        return primaryBitMap;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public void gDataMod(McGlobalData gData) {
        MapCoord coord = new MapCoord(x, z);
        if (!gData.containsMapDataKey(coord)) {
            System.out.println("Incorrect data x=" + x + ", z=" + z);
            return;
        }

        // How many chunks sent
        int chunksSentP = 0;
        boolean[] chunksSentArrayP = new boolean[16];
        int chunksSentA = 0;
        boolean[] chunksSentArrayA = new boolean[16];
        for (int i = 0; i < 16; i++) {
            if ((primaryBitMap & 1 << i) != 0) {
                chunksSentP++;
                chunksSentArrayP[i] = true;
            }
            if ((addBitMap & 1 << i) != 0) {
                System.out.println("ChunksSentA ! i:" + i);
                chunksSentA++;
                chunksSentArrayA[i] = true;
            }
        }

		/*System.out.print("Extracting data for x=" + x + ", z=" + z + ". (" + primaryBitMap + ", " + addBitMap + ") Primary=" + chunksSentP + " [");
        for(int i = 0; i < 16; i++)
			System.out.print(chunksSentArrayP[i]);
		System.out.print("]. Add=" + chunksSentA + " [");
		for(int i = 0; i < 16; i++)
			System.out.print(chunksSentArrayA[i]);
		System.out.println("].");*/
        int uncompressedDataSize = (10240 * chunksSentP + 2048 * chunksSentA + (groundUpContinuous ? 256 : 0));


        Inflater decompresser = new Inflater();
        decompresser.setInput(compressedData, 0, compressedSize);
        byte[] result = new byte[uncompressedDataSize + 1];
        try {
            int resultLength = decompresser.inflate(result);
            //System.out.println("Extracting data for x=" + x + ", z=" + z + ". Size = " + resultLength + "/" +uncompressedDataSize);
            if (resultLength != uncompressedDataSize)
                System.out.println("Error map " + resultLength + "/" + uncompressedDataSize);
        } catch (DataFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        decompresser.end();

        byte[][][] blocks = new byte[16][16][256];
        int offset = 0;
        int lastY = 0;
        for (int i = 0; i < 16; i++) {
            if (chunksSentArrayP[i]) {
                for (int j = 0; j < 4096; j++) {
                    byte bX = (byte) (j & 0x0F);
                    byte bY = (byte) (i * 16 + (j >> 8));
                    byte bZ = (byte) ((j & 0xF0) >> 4);
                    byte data = result[offset + j];
                    try {
                        blocks[bX][bZ][bY & 0xFF] = data;
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println("bX=" + bX + ", bZ=" + bZ + ", bY=" + bY + ", data=" + data + ", offset=" + offset + ", i=" + i + ", j=" + j);
                    }
                    //if(data == 42)
                    //System.out.println("42");
                }
                offset += (2048 * 3) + (chunksSentArrayA[i] ? 2048 : 0);
                lastY = i;
            }
        }

        gData.setMapData(coord, new MapData(coord, blocks, lastY));
    }
}
