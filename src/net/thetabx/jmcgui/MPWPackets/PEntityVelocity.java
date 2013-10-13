package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PEntityVelocity extends MPWPacket {
    private final static short packetId = 0x1C;
    // Last update 74

    // Server to client
    private int eId;
    private short vX;
    private short vY;
    private short vZ;

    public PEntityVelocity(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        vX = in.readShort();
        vY = in.readShort();
        vZ = in.readShort();
    }

    public int geteId() {
        return eId;
    }

    public short getvX() {
        return vX;
    }

    public short getvY() {
        return vY;
    }

    public short getvZ() {
        return vZ;
    }
}
