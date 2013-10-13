package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PAttachEntity extends MPWPacket {
    private final static short packetId = 0x27;
    // Last update 74

    // Server to client
    private int eId;
    private int vehicleId;
    private short leash;

    public PAttachEntity(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        vehicleId = in.readInt();
        leash = in.readUByte();
    }

    public int geteId() {
        return eId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public short getLeash() {
        return leash;
    }
}
