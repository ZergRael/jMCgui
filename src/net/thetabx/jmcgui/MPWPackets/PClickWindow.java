package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWObjects.SlotData;
import net.thetabx.jmcgui.TCPWriter;

public class PClickWindow extends MPWPacket {
    private final static short packetId = 0x66;
    // Last update 74

    // Client to server
    private byte windowId;
    private short slot;
    private byte button;
    private short actionNumber;
    private byte mode;
    private SlotData clickedItem;

    public PClickWindow(byte windowId, short slot, byte button, short actionNumber, byte mode, SlotData clickedItem) {
        super(packetId);
        this.windowId = windowId;
        this.slot = slot;
        this.button = button;
        this.actionNumber = actionNumber;
        this.mode = mode;
        this.clickedItem = clickedItem;
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeByte(windowId);
        out.writeShort(slot);
        out.writeByte(button);
        out.writeShort(actionNumber);
        out.writeByte(mode);
        out.writeSlotData(clickedItem);
    }
}
