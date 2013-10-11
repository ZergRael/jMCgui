package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PClickWindow extends MPWPacket{

	private final static short packetId = 0x66;
	
	// Client to server
	private byte windowId;
	private short slot;
	private boolean rightClick;
	private short actionNumber;
	private boolean shift;
	private SlotData clickedItem;
	
	public PClickWindow(byte windowId, short slot, boolean rightClick, short actionNumber, boolean shift, SlotData clickedItem) {
		super(packetId);
		this.windowId = windowId;
		this.slot = slot;
		this.rightClick = rightClick;
		this.actionNumber = actionNumber;
		this.shift = shift;
		this.clickedItem = clickedItem;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeByte(windowId);
		out.writeShort(slot);
		out.writeBool(rightClick);
		out.writeShort(actionNumber);
		out.writeBool(shift);
		clickedItem.send(out);
	}
}
