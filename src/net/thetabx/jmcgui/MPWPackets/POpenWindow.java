package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class POpenWindow extends MPWPacket{

	private final static short packetId = 0x64;
	
	// Server to client
	private byte windowId;
	private byte inventoryType;
	private String windowTitle;
	private byte numberOfSlots;
	
	public POpenWindow(TCPReader in) throws Exception {
		super(packetId);
		this.windowId = in.readByte();
		this.inventoryType = in.readByte();
		this.windowTitle = in.readString();
		this.numberOfSlots = in.readByte();
	}

	public byte getWindowId() {
		return windowId;
	}

	public byte getInventoryType() {
		return inventoryType;
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public byte getNumberOfSlots() {
		return numberOfSlots;
	}
}
