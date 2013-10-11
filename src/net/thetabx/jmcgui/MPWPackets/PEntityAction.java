package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PEntityAction extends MPWPacket{

	private final static short packetId = 0x13;
	
	// Client to server
	private int eId;
	private byte actionId;
	
	// Crouch = 1, Uncrouch = 2, LeaveBed = 3, StartSprinting = 4, StopSprinting = 5;
	
	public PEntityAction(int eId, byte actionId)
	{
		super(packetId);
		this.eId = eId;
		this.actionId = actionId;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeInt(eId);
		out.writeByte(actionId);
	}
	
	public int geteId()
	{
		return eId;
	}
	
	public byte getActionId()
	{
		return actionId;
	}
}
