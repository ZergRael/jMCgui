package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PEntityLook extends MPWPacket{

	private final static short packetId = 0x20;
	
	// Server to client
	private int eId;
	private byte yaw;
	private byte pitch;
	
	public PEntityLook(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.yaw = in.readByte();
		this.pitch = in.readByte();
	}

	public int geteId() {
		return eId;
	}

	public byte getPitch() {
		return pitch;
	}

	public byte getYaw() {
		return yaw;
	}
}
