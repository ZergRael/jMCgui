package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnObjectVehicle extends MPWPacket{

	private final static short packetId = 0x17;
	
	// Server to client
	private int eId;
	private byte type;
	private int x;
	private int y;
	private int z;
	private int fireballThrowerEId;
	private short speedX;
	private short speedY;
	private short speedZ;
	
	public PSpawnObjectVehicle(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.type = in.readByte();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
		this.fireballThrowerEId = in.readInt();
		if(this.fireballThrowerEId > 0)
		{
			this.speedX = in.readShort();
			this.speedY = in.readShort();
			this.speedZ = in.readShort();
		}
	}

	public int geteId() {
		return eId;
	}
	
	public short getSpeedX() {
		return speedX;
	}

	public short getSpeedY() {
		return speedY;
	}

	public short getSpeedZ() {
		return speedZ;
	}

	public byte getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
}
