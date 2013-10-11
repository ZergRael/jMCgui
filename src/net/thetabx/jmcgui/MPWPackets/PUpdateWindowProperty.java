package net.thetabx.jmcgui.MPWPackets;


import net.thetabx.jmcgui.TCPReader;

public class PUpdateWindowProperty extends MPWPacket{

	private final static short packetId = 0x69;
	
	// Server to client
	private byte windowId;
	private short property;
	private short value;
	
	public PUpdateWindowProperty(TCPReader in) throws Exception {
		super(packetId);
		this.windowId = in.readByte();
		this.property = in.readShort();
		this.value = in.readShort();
	}

	public byte getWindowId() {
		return windowId;
	}

	public short getProperty() {
		return property;
	}

	public short getValue() {
		return value;
	}
}
