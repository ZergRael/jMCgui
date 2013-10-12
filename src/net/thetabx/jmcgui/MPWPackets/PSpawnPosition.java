package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnPosition extends MPWPacket{
	private final static short packetId = 0x06;
    // Last update 74
	
	// Server to client
	private int x;
	private int y;
	private int z;
	
	public PSpawnPosition(TCPReader in) throws Exception {
		super(packetId);
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
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
