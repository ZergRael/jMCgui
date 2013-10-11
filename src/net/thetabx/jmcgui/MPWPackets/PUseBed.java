package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PUseBed extends MPWPacket{

	private final static short packetId = 0x11;
	
	// Server to client
	private int eId;
	private int x;
	private byte y;
	private int z;
	
	public PUseBed(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		in.readByte();
		this.x = in.readInt();
		this.y = in.readByte();
		this.z = in.readInt();
	}

	public int geteId() {
		return eId;
	}

	public int getX() {
		return x;
	}

	public byte getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
}
