package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnPainting extends MPWPacket{

	private final static short packetId = 0x19;
	
	// Server to client
	private int eId;
	private String title;
	private int x;
	private int y;
	private int z;
	private int direction;
	
	public PSpawnPainting(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.title = in.readString();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
		this.direction = in.readInt();
	}

	public int getDirection() {
		return direction;
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

	public String getTitle() {
		return title;
	}
}
