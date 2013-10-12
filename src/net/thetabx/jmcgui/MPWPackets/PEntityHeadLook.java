package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PEntityHeadLook extends MPWPacket{
	private final static short packetId = 0x23;
    // Last update 74
	
	// Server to client
	private int eId;
	private byte headYaw;
	
	public PEntityHeadLook(TCPReader in) throws Exception {
		super(packetId);
		eId = in.readInt();
		headYaw = in.readByte();
	}

	public int geteId() {
		return eId;
	}

	public byte getHeadYaw() {
		return headYaw;
	}
}
