package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PCloseWindow extends MPWPacket{

	private final static short packetId = 0x65;
	
	// Two way
	private byte windowId;
	
	public PCloseWindow(TCPReader in) throws Exception {
		super(packetId);
		this.windowId = in.readByte();
	}
	
	public PCloseWindow(byte windowId)
	{
		super(packetId);
		this.windowId = windowId;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeByte(windowId);
	}
	
	public byte getWindowId()
	{
		return windowId;
	}
}
