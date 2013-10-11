package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSoundParticleEffect extends MPWPacket{

	private final static short packetId = 0x3D;
	
	// Server to client
	private int eId;
	private int x;
	private byte y;
	private int z;
	private int data;
	
	public PSoundParticleEffect(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.x = in.readInt();
		this.y = in.readByte();
		this.z = in.readInt();
		this.data = in.readInt();
	}
	
	public int geteId()
	{
		return this.eId;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public byte getY()
	{
		return this.y;
	}
	
	public int getZ()
	{
		return this.z;
	}
	
	public int getData()
	{
		return this.data;
	}
}
