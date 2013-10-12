package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWObjects.SlotData;
import net.thetabx.jmcgui.TCPReader;

public class PSetSlot extends MPWPacket{

	private final static short packetId = 0x67;
	
	// Server to client
	private byte windowId;
	private short slot;
	private SlotData slotData;

	
	public PSetSlot(TCPReader in) throws Exception {
		super(packetId);
		this.windowId = in.readByte();
		this.slot = in.readShort();
		
		// slot
		slotData = new SlotData(in);
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
