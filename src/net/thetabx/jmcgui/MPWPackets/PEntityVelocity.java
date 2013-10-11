package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PEntityVelocity extends MPWPacket{

	private final static short packetId = 0x1C;
	
	// Server to client
	private int eId;
	private short vX;
	private short vY;
	private short vZ;
	
	public PEntityVelocity(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.vX = in.readShort();
		this.vY = in.readShort();
		this.vZ = in.readShort();
	}

	public int geteId() {
		return eId;
	}

	public short getvX() {
		return vX;
	}

	public short getvY() {
		return vY;
	}

	public short getvZ() {
		return vZ;
	}
}
