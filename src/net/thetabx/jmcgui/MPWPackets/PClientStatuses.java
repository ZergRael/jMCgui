package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 11/10/13
 * Time: 00:08
 */
public class PClientStatuses extends MPWPacket {
    public static final short packetId = 0xCD;
    // Last update 74

    // Client to server
    private final byte payload;

    public PClientStatuses(byte payload) {
        super(packetId);
        this.payload = payload;
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeByte(payload);
    }
}
