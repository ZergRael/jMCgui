package net.thetabx.jmcgui.MPWPackets;


import net.thetabx.jmcgui.TCPReader;

public class PEntityStatus extends MPWPacket{

	private final static short packetId = 0x26;
	
	// Server to client
	private int eId;
	private byte status;
	
	public PEntityStatus(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.status = in.readByte();
	}

	public int geteId() {
		return eId;
	}

	public byte getStatus() {
		return status;
	}
}
