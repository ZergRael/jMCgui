package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PBlockAction extends MPWPacket {
    private final static short packetId = 0x36;
    // Last update 74

    // Server to client
    private int x;
    private short y;
    private int z;
    private byte b1;
    private byte b2;
    private short blockId;

    public PBlockAction(TCPReader in) throws Exception {
        super(packetId);
        x = in.readInt();
        y = in.readShort();
        z = in.readInt();
        b1 = in.readByte();
        b2 = in.readByte();
        blockId = in.readShort();
    }

    public byte getB1() {
        return b1;
    }

    public byte getB2() {
        return b2;
    }

    public int getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public short getBlockId() {
        return blockId;
    }
}
