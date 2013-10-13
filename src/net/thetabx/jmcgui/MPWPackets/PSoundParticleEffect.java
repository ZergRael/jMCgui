package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSoundParticleEffect extends MPWPacket {
    private final static short packetId = 0x3D;
    // Last update 74

    // Server to client
    private int eId;
    private int x;
    private byte y;
    private int z;
    private int data;
    private boolean disableRelativeVolume;

    public PSoundParticleEffect(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        x = in.readInt();
        y = in.readByte();
        z = in.readInt();
        data = in.readInt();
        disableRelativeVolume = in.readBool();
    }

    public int geteId() {
        return this.eId;
    }

    public int getX() {
        return this.x;
    }

    public byte getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public int getData() {
        return this.data;
    }

    public boolean isDisableRelativeVolume() {
        return disableRelativeVolume;
    }
}
