package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PHeldItemChange extends MPWPacket {
    private final static short packetId = 0x10;
    // Last update 74

    // Two way
    private short slotId;

    public PHeldItemChange(short slotId) {
        super(packetId);
        this.slotId = slotId;
    }

    public PHeldItemChange(TCPReader in) throws Exception {
        super(packetId);
        slotId = in.readShort();
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeShort(slotId);
    }

    private short getSlotId() {
        return slotId;
    }
}
