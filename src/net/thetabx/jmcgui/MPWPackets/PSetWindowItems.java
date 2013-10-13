package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWObjects.SlotData;
import net.thetabx.jmcgui.TCPReader;

public class PSetWindowItems extends MPWPacket {
    private final static short packetId = 0x68;
    // Last update 74

    // Server to client
    private byte windowId;
    private short count;
    private SlotData[] slotData;

    public PSetWindowItems(TCPReader in) throws Exception {
        super(packetId);
        windowId = in.readByte();
        count = in.readShort();
        slotData = new SlotData[count];
        for (int i = 0; i < count; i++) {
            slotData[i] = in.readSlotData();
        }
    }

    public byte getWindowId() {
        return windowId;
    }

    public short getCount() {
        return count;
    }

    public SlotData[] getSlotData() {
        return slotData;
    }
}
