package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PUpdateTileEntity extends MPWPacket {
    private final static short packetId = 0x84;
    // Last update 74

    // Server to client
    private int x;
    private short y;
    private int z;
    private byte action;
    private short dataLength;
    private byte[] nbtData;

    public PUpdateTileEntity(TCPReader in) throws Exception {
        super(packetId);
        this.x = in.readInt();
        this.y = in.readShort();
        this.z = in.readInt();
        this.action = in.readByte();
        dataLength = in.readShort();
        if (dataLength > 0)
            nbtData = in.readByteArray(dataLength);
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

    public byte getAction() {
        return action;
    }

    public short getDataLength() {
        return dataLength;
    }

    public byte[] getNbtData() {
        return nbtData;
    }
}
