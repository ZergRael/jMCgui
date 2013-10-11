package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWEntities.MPWEntity;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PEntityLookRelativeMove extends MPWPacket{

	private final static short packetId = 0x21;
	
	// Server to client
	private int eId;
	private byte dX;
	private byte dY;
	private byte dZ;
	private byte yaw;
	private byte pitch;
	
	public PEntityLookRelativeMove(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.dX = in.readByte();
		this.dY = in.readByte();
		this.dZ = in.readByte();
		this.yaw = in.readByte();
		this.pitch = in.readByte();
	}

	public double getdX() {
		return (double)dX/32;
	}

	public double getdY() {
		return (double)dY/32;
	}

	public double getdZ() {
		return (double)dZ/32;
	}

	public int geteId() {
		return eId;
	}

	public byte getPitch() {
		return pitch;
	}

	public byte getYaw() {
		return yaw;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		synchronized(gData)
		{
			MPWEntity e = gData.getEntity(this.geteId());
			if(e != null)
				e.setXYZ(e.getX() + this.getdX(), e.getY() + this.getdY(), e.getZ() + this.getdZ());
		}
	}
}
