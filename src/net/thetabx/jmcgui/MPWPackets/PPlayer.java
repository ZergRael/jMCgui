package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PPlayer extends MPWPacket{
	private final static short packetId = 0x0A;
    // Last update 74
	
	// Client to server
	private boolean onGround;
	
	public PPlayer(boolean onGround)
	{
		super(packetId);
		this.onGround = onGround;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeBool(onGround);
	}
}
