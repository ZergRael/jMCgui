package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PEntity extends MPWPacket {
    private final static short packetId = 0x1E;
    // Last update 74

    // Server to client
    private int eId;

    public PEntity(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeInt(eId);
    }

    public int geteId() {
        return eId;
    }
}
