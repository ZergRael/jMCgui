package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PAttachEntity extends MPWPacket{

	private final static short packetId = 0x27;
	
	// Server to client
	private int eId;
	private int vehicleId;
	
	public PAttachEntity(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.vehicleId = in.readInt();
	}
	
	public int geteId()
	{
		return eId;
	}
	
	public int getVehicleId()
	{
		return vehicleId;
	}
}
