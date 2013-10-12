package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnExperienceOrb extends MPWPacket{
	private final static short packetId = 0x1A;
    // Last update 74
	
	// Server to client
	private int eId;
	private int x;
	private int y;
	private int z;
	private short count;
	
	public PSpawnExperienceOrb(TCPReader in) throws Exception {
		super(packetId);
		eId = in.readInt();
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
		count = in.readShort();
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
