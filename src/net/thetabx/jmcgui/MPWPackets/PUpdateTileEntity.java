package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PUpdateTileEntity extends MPWPacket{

	private final static short packetId = 0x84;
	
	// Server to client
	private int x;
	private short y;
	private int z;
	private byte action;
	private int custom1;
	private int custom2;
	private int custom3;
	
	public PUpdateTileEntity(TCPReader in) throws Exception {
		super(packetId);
		this.x = in.readInt();
		this.y = in.readShort();
		this.z = in.readInt();
		this.action = in.readByte();
		this.custom1 = in.readInt();
		this.custom2 = in.readInt();
		this.custom3 = in.readInt();
	}

	public int getX() {
		return x;
	}

	public short getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public byte getAction() {
		return action;
	}

	public int getCustom1() {
		return custom1;
	}

	public int getCustom2() {
		return custom2;
	}

	public int getCustom3() {
		return custom3;
	}
}
