package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PPlayerBlockPlacement extends MPWPacket{

	private final static short packetId = 0x0F;
	
	// Client to server
	private int x;
	private short y;
	private int z;
	private byte direction;
	private SlotData slotData;
	// TODO slot
	
	public PPlayerBlockPlacement(int x, short y, int z, byte direction, SlotData slotData)
	{
		super(packetId);
		this.x = x;
		this.y = y;
		this.z = z;
		this.direction = direction;
		this.slotData = slotData;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeInt(x);
		out.writeUByte(y);
		out.writeInt(z);
		out.writeByte(direction);
		slotData.send(out);
	}
}
