package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWObjects.SlotData;
import net.thetabx.jmcgui.TCPReader;

public class PEntityEquipment extends MPWPacket{
	private final static short packetId = 0x05;
    // Last update 74
	
	// Server to client
	private int eId;
	private short slot;
    private SlotData slotData;
	
	public PEntityEquipment(TCPReader in) throws Exception {
		super(packetId);
		eId = in.readInt();
		slot = in.readShort();
        slotData = new SlotData(in);
	}

	public int geteId() {
		return eId;
	}

	public short getSlot() {
		return slot;
	}

    public SlotData getSlotData() {
        return slotData;
    }
}
