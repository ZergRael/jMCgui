package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnPosition extends MPWPacket{

	private final static short packetId = 0x06;
	
	// Server to client
	private int x;
	private int y;
	private int z;
	
	public PSpawnPosition(TCPReader in) throws Exception {
		super(packetId);
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
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
