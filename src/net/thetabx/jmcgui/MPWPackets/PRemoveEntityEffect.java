package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PRemoveEntityEffect extends MPWPacket{
	private final static short packetId = 0x2A;
    // Last update 74
	
	// Server to client
	private int eId;
	private byte effectId;
	
	public PRemoveEntityEffect(TCPReader in) throws Exception {
		super(packetId);
		eId = in.readInt();
		effectId = in.readByte();
	}
	
	public int geteId() {
		return eId;
	}

	public byte getEffectId() {
		return effectId;
	}
}
