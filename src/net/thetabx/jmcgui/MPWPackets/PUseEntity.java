package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PUseEntity extends MPWPacket{
	private final static short packetId = 0x07;
    // Last update 74
	
	// Client to server
	private int userEId;
	private int targetEId;
	private boolean mouseButton; // true: left click / false: right click
	
	public PUseEntity(int userEId, int targetEId, boolean mouseButton)
	{
		super(packetId);
		this.userEId = userEId;
		this.targetEId = targetEId;
		this.mouseButton = mouseButton;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeInt(userEId);
		out.writeInt(targetEId);
		out.writeBool(mouseButton);
	}
}
