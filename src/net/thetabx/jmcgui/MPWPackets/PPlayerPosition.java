package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PPlayerPosition extends MPWPacket{

	private final static short packetId = 0x0B;
	
	// Client to server
	private double x;
	private double y;
	private double stance;
	private double z;
	private boolean onGround;
	
	public PPlayerPosition(double x, double y, double stance, double z, boolean onGround)
	{
		super(packetId);
		this.x = x;
		this.y = y;
		this.stance = stance;
		this.z = z;
		this.onGround = onGround;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeDouble(x);
		out.writeDouble(y);
		out.writeDouble(stance);
		out.writeDouble(z);
		out.writeBool(onGround);
	}
}
