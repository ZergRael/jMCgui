package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnExperienceOrb extends MPWPacket{

	private final static short packetId = 0x1A;
	
	// Server to client
	private int eId;
	private int x;
	private int y;
	private int z;
	private short count;
	
	public PSpawnExperienceOrb(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
		this.count = in.readShort();
	}

	public int geteId() {
		return eId;
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

	public short getCount() {
		return count;
	}
}
