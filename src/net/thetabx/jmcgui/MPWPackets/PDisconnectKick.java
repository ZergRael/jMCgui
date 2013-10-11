package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PDisconnectKick extends MPWPacket{

	private final static short packetId = 0xFF;
	
	// Two way
	private String reason;
	
	public PDisconnectKick(TCPReader in) throws Exception {
		super(packetId);
		this.reason = in.readString();
	}
	
	public PDisconnectKick(String reason)
	{
		super(packetId);
		this.reason = reason;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeString(reason);
	}
	
	public String getReason()
	{
		return reason;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		gData.stop(McGlobalData.StopReason.KICK, reason);
	}
}