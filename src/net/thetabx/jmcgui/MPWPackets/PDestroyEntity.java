package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PDestroyEntity extends MPWPacket{

	private final static short packetId = 0x1D;
	
	// Server to client
	private int eId;
	
	public PDestroyEntity(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
	}

	public int geteId() {
		return eId;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		synchronized(gData)
		{
			gData.removeEntity(this.geteId());
		}
	}
}
