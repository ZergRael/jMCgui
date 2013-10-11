package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWEntities.EPlayer;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PSpawnNamedEntity extends MPWPacket{

	private final static short packetId = 0x14;
	
	// Server to client
	private int eId;
	private String playerName;
	private int x;
	private int y;
	private int z;
	private byte yaw;
	private byte pitch;
	private short currentItem;
	
	public PSpawnNamedEntity(TCPReader in) throws Exception {
		super(packetId);
		this.eId = in.readInt();
		this.playerName = in.readString();
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
		this.yaw = in.readByte();
		this.pitch = in.readByte();
		this.currentItem = in.readShort();
	}

	public short getCurrentItem() {
		return currentItem;
	}

	public int geteId() {
		return eId;
	}

	public byte getPitch() {
		return pitch;
	}

	public String getPlayerName() {
		return playerName;
	}

	public double getX() {
		return (double)x/32;
	}

	public double getY() {
		return (double)y/32;
	}
	public byte getYaw() {
		return yaw;
	}

	public double getZ() {
		return (double)z/32;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		synchronized(gData)
		{
			gData.setEntity(this.geteId(), new EPlayer(this.geteId(), this.getX(), this.getY(), this.getZ(), playerName));
		}
	}
}
