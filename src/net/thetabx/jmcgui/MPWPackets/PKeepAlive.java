package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PKeepAlive extends MPWPacket{

	private final static short packetId = 0x00;
	
	// Two way
	private int keepAliveId;
	
	public PKeepAlive(TCPReader in) throws Exception {
		super(packetId);
		this.keepAliveId = in.readInt();
	}
	
	public PKeepAlive(int keepAliveId)
	{
		super(packetId);
		this.keepAliveId = keepAliveId;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeInt(keepAliveId);
	}
	
	public MPWPacket getResponsePacket(McGlobalData gData)
	{
		return this;
	}
}
