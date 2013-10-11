package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.DataTypes.MapCoord;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PMapColumnAllocation extends MPWPacket{

	private final static short packetId = 0x32;
	
	// Two way
	private int x;
	private int z;
	private Boolean mode;
	
	public PMapColumnAllocation(TCPReader in) throws Exception {
		super(packetId);
		this.x = in.readInt();
		this.z = in.readInt();
		this.mode = in.readBool();
	}

	public Boolean getMode() {
		return mode;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}
	
	public void gDataMod(McGlobalData gData) {
		if(mode) {
			gData.allocMapData(new MapCoord(x, z));
			//System.out.println("Received MapAlloc for x:" + x + " z:" + z);
		}
		else
			gData.removeMapData(new MapCoord(x, z));
	}
}
