package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWEntities.MPWEntity;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PEntityTeleport extends MPWPacket {
    private final static short packetId = 0x22;
    // Last update 74

    // Server to client
    private int eId;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte ptich;

    public PEntityTeleport(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        yaw = in.readByte();
        ptich = in.readByte();
    }

    public int geteId() {
        return eId;
    }

    public double getX() {
        return (double) x / 32;
    }

    public double getY() {
        return (double) y / 32;
    }

    public double getZ() {
        return (double) z / 32;
    }

    public byte getYaw() {
        return yaw;
    }

    public byte getPtich() {
        return ptich;
    }

    public void gDataMod(McGlobalData gData) {
        synchronized (gData.entities) {
            MPWEntity e = gData.getEntity(this.geteId());
            if (e != null)
                e.setXYZ(this.getX(), this.getY(), this.getZ());
        }
    }
}
