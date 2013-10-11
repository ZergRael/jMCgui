package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PEntityEquipment extends MPWPacket{

	private final static short packetId = 0x05;
	
	// Server to client
	private int eId;
	private short slot;
	private short itemId;
	private short damage;
	
	public PEntityEquipment(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.slot = in.readShort();
		this.itemId = in.readShort();
		this.damage = in.readShort();
	}

	public short getDamage() {
		return damage;
	}

	public int geteId() {
		return eId;
	}

	public short getItemId() {
		return itemId;
	}

	public short getSlot() {
		return slot;
	}
}
