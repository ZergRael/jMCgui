package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PPlayerDigging extends MPWPacket{

	private final static short packetId = 0x0E;
	
	// Client to server
	private byte status;
	private int x;
	private byte y;
	private int z;
	private byte face;
	
	public PPlayerDigging(byte status, int x, byte y, int z, byte face)
	{
		super(packetId);
		this.status = status;
		this.x = x;
		this.y = y;
		this.z = z;
		this.face = face;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeByte(status);
		out.writeInt(x);
		out.writeByte(y);
		out.writeInt(z);
		out.writeByte(face);
	}
}
