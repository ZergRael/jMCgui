package net.thetabx.jmcgui.MPWPackets;

import java.util.ArrayList;

import net.thetabx.jmcgui.MPWEntities.EMob;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PSpawnMob extends MPWPacket{

	private final static short packetId = 0x18;
	
	// Server to client
	private int eId;
	private byte type;
	private int x;
	private int y;
	private int z;
	private byte yaw;
	private byte ptich;
	private byte headYaw;
	private ArrayList<McMetadata> metadata;
	
	public PSpawnMob(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.type = in.readByte();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
		this.yaw = in.readByte();
		this.ptich = in.readByte();
		this.headYaw = in.readByte();
		this.metadata = in.readMetadata();
		
		if(this.getY() > 255)
			throw new Exception("FU int Y:" + this.y + " doubleY:" + this.getY());
	}

	public int geteId() {
		return eId;
	}
	
	public byte getType() {
		return type;
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

	public byte getHeadYaw() {
		return headYaw;
	}

	public ArrayList<McMetadata> getMetadata() {
		return metadata;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		synchronized(gData)
		{
			gData.setEntity(this.geteId(), new EMob(this.geteId(), this.getX(), this.getY(), this.getZ(), type));
		}
	}
}
