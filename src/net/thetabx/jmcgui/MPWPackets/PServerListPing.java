package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PServerListPing extends MPWPacket{

	private final static short packetId = 0xFE;

	// Client to server
	private final byte magicByte = (byte) 0x01;
	
	public PServerListPing()
	{
		super(packetId);
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeUByte(magicByte);
	}
}
