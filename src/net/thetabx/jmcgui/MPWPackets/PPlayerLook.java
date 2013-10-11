package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PPlayerLook extends MPWPacket{

	private final static short packetId = 0x0C;
	
	// Client to server
	private float yaw;
	private float pitch;
	private boolean onGround;
	
	public PPlayerLook(float yaw, float pitch, boolean onGround)
	{
		super(packetId);
		this.yaw = yaw;
		this.pitch = pitch;
		this.onGround = onGround;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeFloat(yaw);
		out.writeFloat(pitch);
		out.writeBool(onGround);
	}
}
