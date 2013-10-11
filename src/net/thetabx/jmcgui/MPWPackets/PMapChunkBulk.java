package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 11/10/13
 * Time: 01:15
 */
public class PMapChunkBulk extends MPWPacket {
    public static final short packetId = 0x38;

    // Server to client
    private short chunkColumnCount;
    private int dataLength;
    private boolean skyLightSent;
    private byte[] data;
    private MetaInformation[] metaInformation;

    public PMapChunkBulk(TCPReader in) throws Exception {
        super(packetId);
        chunkColumnCount = in.readShort();
        dataLength = in.readInt();
        System.out.println(dataLength);
        skyLightSent = in.readBool();
        data = in.readByteArray(dataLength);
        metaInformation = new MetaInformation[chunkColumnCount];
        for(short i = 0; i < chunkColumnCount; i++)
        {
            metaInformation[i] = new MetaInformation(in.readInt(), in.readInt(), in.readUShort(), in.readUShort());
        }
    }

    public short getChunkColumnCount() {
        return chunkColumnCount;
    }

    public int getDataLength() {
        return dataLength;
    }

    public boolean isSkyLightSent() {
        return skyLightSent;
    }

    public byte[] getData() {
        return data;
    }

    public MetaInformation[] getMetaInformation() {
        return metaInformation;
    }

    class MetaInformation {
        public int chunkX;
        public int chunkZ;
        public int primaryBitmap;
        public int addBitmap;

        public MetaInformation(int chunkX, int chunkZ, int primaryBitmap, int addBitmap)
        {
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
            this.primaryBitmap = primaryBitmap;
            this.addBitmap = addBitmap;
        }
    }
}
