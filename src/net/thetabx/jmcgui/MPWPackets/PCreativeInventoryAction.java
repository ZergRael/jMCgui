package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWObjects.SlotData;
import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PCreativeInventoryAction extends MPWPacket {
    private final static short packetId = 0x6B;
    // Last update 74

    // Two Way
    private short slot;
    private SlotData clickedItem;

    public PCreativeInventoryAction(TCPReader in) throws Exception {
        super(packetId);
        slot = in.readShort();
        clickedItem = in.readSlotData();
    }

    public PCreativeInventoryAction(short slot, SlotData clickedItem) {
        super(packetId);
        this.slot = slot;
        this.clickedItem = clickedItem;
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeShort(slot);
        out.writeSlotData(clickedItem);
    }

    public short getSlot() {
        return slot;
    }

    public SlotData getClickedItem() {
        return clickedItem;
    }
}
