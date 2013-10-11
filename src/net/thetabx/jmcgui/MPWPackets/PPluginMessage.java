package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PPluginMessage extends MPWPacket{

	private final static short packetId = 0xFA;
	
	// Two Way
	private String channel;
	private short length;
	private byte[] data;
	private String message;
	
	public PPluginMessage(TCPReader in) throws Exception {
		super(packetId);
		this.channel = in.readString();
		this.length = in.readShort();
        this.data = in.readByteArray(length);
	}
	
	public PPluginMessage(String channel, byte[] data)
	{
		super(packetId);
		this.channel = channel;
		this.length = (short) data.length;
		this.data = data;
	}
	
	public PPluginMessage(String channel, String message)
	{
		super(packetId);
		this.channel = channel;
		this.message = message;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeString(channel);
		if(message != null) {
			out.writeShort(length);
			for(int i = 0; i < length; i++)
				out.writeByte(data[i]);
		}
		else {
			out.writeString(message);
		}
	}
}
