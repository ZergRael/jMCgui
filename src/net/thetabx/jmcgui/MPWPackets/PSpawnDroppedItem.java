package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnDroppedItem extends MPWPacket{

	private final static short packetId = 0x15;
	
	// Server to client
	private int eId;
	private short item;
	private byte count;
	private short damageData;
	private int x;
	private int y;
	private int z;
	private byte rotation;
	private byte pitch;
	private byte roll;
	
	public PSpawnDroppedItem(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.item = in.readShort();
		this.count = in.readByte();
		this.damageData = in.readShort();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
		this.rotation = in.readByte();
		this.pitch = in.readByte();
		this.roll = in.readByte();
	}

	public byte getCount() {
		return count;
	}

	public short getDamageData() {
		return damageData;
	}

	public int geteId() {
		return eId;
	}

	public short getItem() {
		return item;
	}

	public byte getPitch() {
		return pitch;
	}

	public byte getRoll() {
		return roll;
	}

	public byte getRotation() {
		return rotation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
}
