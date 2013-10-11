package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PEntityHeadLook extends MPWPacket{

	private final static short packetId = 0x23;
	
	// Server to client
	private int eId;
	private byte headYaw;
	
	public PEntityHeadLook(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.headYaw = in.readByte();
	}

	public int geteId() {
		return eId;
	}

	public byte getHeadYaw() {
		return headYaw;
	}
}
