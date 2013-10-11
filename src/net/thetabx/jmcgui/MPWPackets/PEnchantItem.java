package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PEnchantItem extends MPWPacket{

	private final static short packetId = 0x6C;
	
	// Client to server
	private byte windowId;
	private byte enchantment;
	
	public PEnchantItem(byte windowId, byte enchantment)
	{
		super(packetId);
		this.windowId = windowId;
		this.enchantment = enchantment;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeByte(windowId);
		out.writeByte(enchantment);
	}
}
