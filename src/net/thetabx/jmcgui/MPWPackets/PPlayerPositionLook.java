package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWEntities.ESelf;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PPlayerPositionLook extends MPWPacket{
	private final static short packetId = 0x0D;
    // Last update 74
	
	// Two way
	private double x;
	private double y;
	private double stance;
	private double z;
	private float yaw;
	private float pitch;
	private boolean onGround;
	
	public PPlayerPositionLook(TCPReader in) throws Exception {
		super(packetId);
		x = in.readDouble();
		stance = in.readDouble();
		y = in.readDouble();
		z = in.readDouble();
		yaw = in.readFloat();
		pitch = in.readFloat();
		onGround = in.readBool();
	}
	
	public PPlayerPositionLook(double x, double y, double stance, double z, float yaw, float pitch, boolean onGround)
	{
		super(packetId);
		this.x = x;
		this.y = y;
		this.stance = stance;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.onGround = onGround;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeDouble(x);
		out.writeDouble(y);
		out.writeDouble(stance);
		out.writeDouble(z);
		out.writeFloat(yaw);
		out.writeFloat(pitch);
		out.writeBool(onGround);
	}
	
	public void gDataMod(McGlobalData gData)
	{
		synchronized(gData)
		{
			if(gData.getEntity(gData.getPlayerEntityId()) == null)
				gData.setEntity(gData.getPlayerEntityId(), new ESelf(gData.getPlayerEntityId(), x, y, z, gData.getNickname(), stance, yaw, pitch, onGround));
			else {
				ESelf self = (ESelf) gData.getEntity(gData.getPlayerEntityId());
				self.setXYZ(x, y, z);
				self.setStance(stance);
				self.setYaw(yaw);
				self.setPtich(pitch);
				self.setOnGround(onGround);
			}
		}
	}
	
	public MPWPacket getResponsePacket(McGlobalData gData)
	{
		return this;
	}
}
