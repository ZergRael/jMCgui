package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnGlobalEntity extends MPWPacket {
    private final static short packetId = 0x47;
    // Last update 74

    // Server to client
    private int eId;
    private byte type;
    private int x;
    private int y;
    private int z;

    public PSpawnGlobalEntity(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        type = in.readByte();
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
    }

    public int geteId() {
        return this.eId;
    }

    public byte getType() {
        return type;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }
}
