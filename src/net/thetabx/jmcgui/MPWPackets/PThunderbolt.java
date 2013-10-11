package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PThunderbolt extends MPWPacket{

	private final static short packetId = 0x47;
	
	// Server to client
	private int eId;
	private int x;
	private int y;
	private int z;
	
	public PThunderbolt(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		in.readBool();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
	}

	public int geteId() {
		return this.eId;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getZ() {
		return this.z;
	}
}
