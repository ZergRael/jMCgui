package net.thetabx.jmcgui.MPWPackets;


import java.util.ArrayList;

import net.thetabx.jmcgui.MPWObjects.Metadata;
import net.thetabx.jmcgui.TCPReader;

public class PEntityMetadata extends MPWPacket{
	private final static short packetId = 0x28;
    // Last update 74
	
	// Server to client
	private int eId;
	private ArrayList<Metadata> metadata;
	
	public PEntityMetadata(TCPReader in) throws Exception {
		super(packetId);
		eId = in.readInt();
		metadata = in.readMetadata();
	}

	public int geteId() {
		return eId;
	}

	public ArrayList<Metadata> getMetadata() {
		return metadata;
	}
}
