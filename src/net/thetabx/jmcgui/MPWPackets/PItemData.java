package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PItemData extends MPWPacket{

	private final static short packetId = 0x83;
	
	// Server to client
	private short itemType;
	private short itemId;
	private short textLength;
	private byte[] text;
	
	public PItemData(TCPReader in) throws Exception {
		super(packetId);
		this.itemType = in.readShort();
		this.itemId = in.readShort();
		this.textLength = in.readUByte();
		this.text = new byte[textLength];
		for(int i = 0; i < textLength; i++)
			this.text[i] = in.readByte();
	}

	public short getItemType() {
		return itemType;
	}

	public short getItemId() {
		return itemId;
	}

	public short getTextLength() {
		return textLength;
	}

	public byte[] getText() {
		return text;
	}
}
