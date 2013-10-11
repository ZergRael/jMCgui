package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWEntities.MPWEntity;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PEntityTeleport extends MPWPacket{

	private final static short packetId = 0x22;
	
	// Server to client
	private int eId;
	private int x;
	private int y;
	private int z;
	private byte yaw;
	private byte ptich;
	
	public PEntityTeleport(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
		this.yaw = in.readByte();
		this.ptich = in.readByte();
	}

	public int geteId() {
		return eId;
	}

	public double getX() {
		return (double)x/32;
	}

	public double getY() {
		return (double)y/32;
	}

	public double getZ() {
		return (double)z/32;
	}

	public byte getYaw() {
		return yaw;
	}
	
	public byte getPtich() {
		return ptich;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		synchronized(gData)
		{
			MPWEntity e = gData.getEntity(this.geteId());
			if(e != null)
				e.setXYZ(this.getX(), this.getY(), this.getZ());
		}
	}
}
