package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PConfirmTransaction extends MPWPacket{

	private final static short packetId = 0x6A;
	
	// Two Way
	private byte windowId;
	private short actionNumber;
	private boolean accepted;
	
	public PConfirmTransaction(TCPReader in) throws Exception {
		super(packetId);
		this.windowId = in.readByte();
		this.actionNumber = in.readShort();
		this.accepted = in.readBool();
	}
	
	public PConfirmTransaction(byte windowId, short actionNumber, boolean accepted)
	{
		super(packetId);
		this.windowId = windowId;
		this.actionNumber = actionNumber;
		this.accepted = accepted;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeByte(windowId);
		out.writeShort(actionNumber);
		out.writeBool(accepted);
	}

	public byte getWindowId() {
		return windowId;
	}

	public short getActionNumber() {
		return actionNumber;
	}

	public boolean isAccepted() {
		return accepted;
	}
}
