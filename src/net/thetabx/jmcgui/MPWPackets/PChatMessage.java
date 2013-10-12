package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PChatMessage extends MPWPacket{
	private final static short packetId = 0x03;
    // Last update 74
	
	// Two way
	private String message;
	
	public PChatMessage(TCPReader in) throws Exception {
		super(packetId);
		message = in.readString();
		System.out.println("Chat message : " + message);
        // TODO Process JSON
	}
	
	public PChatMessage(String message)
	{
		super(packetId);
		this.message = message;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeString(message);
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		synchronized(gData.receivedChatMessages)
		{
			gData.addReceivedMessage(message);
		}
	}
}
