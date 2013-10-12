package net.thetabx.jmcgui.MPWPackets;

import java.util.ArrayList;

import net.thetabx.jmcgui.MPWEntities.EMob;
import net.thetabx.jmcgui.MPWObjects.Metadata;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PSpawnMob extends MPWPacket{
	private final static short packetId = 0x18;
    // Last update 74
	
	// Server to client
	private int eId;
	private byte type;
	private int x;
	private int y;
	private int z;
    private byte ptich;
    private byte headPtich;
	private byte yaw;
    private short velocityX;
    private short velocityY;
    private short velocityZ;
	private ArrayList<Metadata> metadata;
	
	public PSpawnMob(TCPReader in) throws Exception {
		super(packetId);
		eId = in.readInt();
		type = in.readByte();
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
        ptich = in.readByte();
        headPtich = in.readByte();
		yaw = in.readByte();
        velocityX = in.readShort();
        velocityY = in.readShort();
        velocityZ = in.readShort();
		metadata = in.readMetadata();
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

    public byte getPtich() {
        return ptich;
    }

    public byte getHeadPtich() {
        return headPtich;
    }
	
	public byte getYaw() {
		return yaw;
	}

    public short getVelocityX() {
        return velocityX;
    }

    public short getVelocityY() {
        return velocityY;
    }

    public short getVelocityZ() {
        return velocityZ;
    }

    public ArrayList<Metadata> getMetadata() {
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
