package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWObjects.SlotData;
import net.thetabx.jmcgui.TCPReader;

public class PSetWindowItems extends MPWPacket{

	// TODO Fix SetWindowItems
	
	private final static short packetId = 0x68;
	
	// Server to client
	private byte windowId;
	private short count;
	
	private SlotData[] slotData;
	
	public PSetWindowItems(TCPReader in) throws Exception {
		super(packetId);
		this.windowId = in.readByte();
		this.count = in.readShort();
		this.slotData = new SlotData[count];
		
		for(int k = 0; k < count; k++)
		{
			// slot
			slotData[k] = new SlotData(in);
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
