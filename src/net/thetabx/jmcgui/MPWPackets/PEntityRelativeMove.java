package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWEntities.MPWEntity;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PEntityRelativeMove extends MPWPacket {
    private final static short packetId = 0x1F;
    // Last update 74

    // Server to client
    private int eId;
    private byte dX;
    private byte dY;
    private byte dZ;

    public PEntityRelativeMove(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        dX = in.readByte();
        dY = in.readByte();
        dZ = in.readByte();
    }

    public double getdX() {
        return (double) dX / 32;
    }

    public double getdY() {
        return (double) dY / 32;
    }

    public double getdZ() {
        return (double) dZ / 32;
    }

    public int geteId() {
        return eId;
    }

    public void gDataMod(McGlobalData gData) {
        synchronized (gData.entities) {
            MPWEntity e = gData.getEntity(this.geteId());
            if (e != null)
                e.setXYZ(e.getX() + getdX(), e.getY() + getdY(), e.getZ() + getdZ());
        }
    }
}
