package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PBlockAction extends MPWPacket{

	private final static short packetId = 0x36;
	
	// Server to client
	private int x;
	private short y;
	private int z;
	private byte b1;
	private byte b2;
	
	public PBlockAction(TCPReader in) throws Exception {
		super(packetId);
		this.x = in.readInt();
		this.y = in.readShort();
		this.z = in.readInt();
		this.b1 = in.readByte();
		this.b2 = in.readByte();
	}

	public byte getB1() {
		return b1;
	}

	public byte getB2() {
		return b2;
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
}
