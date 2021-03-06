package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.DataTypes.MapCoord;
import net.thetabx.jmcgui.DataTypes.MapData;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PBlockChange extends MPWPacket {
    private final static short packetId = 0x35;
    // Last update 74

    // Server to client
    private int x;
    private byte y;
    private int z;
    private short blockType;
    private byte blockMetadata;

    public PBlockChange(TCPReader in) throws Exception {
        super(packetId);
        x = in.readInt();
        y = in.readByte();
        z = in.readInt();
        blockType = in.readShort();
        blockMetadata = in.readByte();
    }

    public int getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public short getBlockType() {
        return blockType;
    }

    public byte getBlockMetadata() {
        return blockMetadata;
    }

    public void gDataMod(McGlobalData gData) {
        MapCoord coord = new MapCoord(x / 16, z / 16);
        MapData map = gData.getMapData(coord);
        if (map == null)
            return;
        byte[][][] data = map.getData();
        if (data == null) {
            System.out.println("Can't modify uncreated data map");
            return;
        }
        byte xChunkRel = (byte) Math.abs(x % 16);
        byte zChunkRel = (byte) Math.abs(z % 16);
        System.out.println("X=" + (coord.getTrueX() + xChunkRel) + " Z=" + (coord.getTrueZ() + zChunkRel) + " x=" + xChunkRel + " y=" + y + " z=" + zChunkRel + " was_id=" + data[xChunkRel][zChunkRel][y] + " now_id=" + blockType);
        //data[xChunkRel][zChunkRel][y] = blockType;
        int lastY = (map.getLastY() > y / 16 ? map.getLastY() : y / 16);
        map.setData(data);
        map.setLastY(lastY);
        map.calcTopLayer();

        gData.setMapData(coord, map);
    }
}
