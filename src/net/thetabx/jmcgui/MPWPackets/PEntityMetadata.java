package net.thetabx.jmcgui.MPWPackets;


import java.util.ArrayList;

import net.thetabx.jmcgui.TCPReader;

public class PEntityMetadata extends MPWPacket{

	private final static short packetId = 0x28;
	
	// Server to client
	private int eId;
	private ArrayList<McMetadata> metadata;
	
	public PEntityMetadata(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.metadata = in.readMetadata();
	}

	public int geteId() {
		return eId;
	}

	public ArrayList<McMetadata> getMetadata() {
		return metadata;
	}
}
