package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PUseBed extends MPWPacket {
    private final static short packetId = 0x11;
    // Last update 74

    // Server to client
    private int eId;
    private int x;
    private byte y;
    private int z;

    public PUseBed(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        in.readByte();
        x = in.readInt();
        y = in.readByte();
        z = in.readInt();
    }

    public int geteId() {
        return eId;
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
}
