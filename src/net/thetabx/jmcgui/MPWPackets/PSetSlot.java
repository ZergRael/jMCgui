package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWObjects.SlotData;
import net.thetabx.jmcgui.TCPReader;

public class PSetSlot extends MPWPacket {
    private final static short packetId = 0x67;
    // Last update 74

    // Server to client
    private byte windowId;
    private short slot;
    private SlotData slotData;

    public PSetSlot(TCPReader in) throws Exception {
        super(packetId);
        windowId = in.readByte();
        slot = in.readShort();
        slotData = in.readSlotData();
    }

    public SlotData getSlotData() {
        return slotData;
    }

    public short getSlot() {
        return slot;
    }

    public byte getWindowId() {
        return windowId;
    }
}
