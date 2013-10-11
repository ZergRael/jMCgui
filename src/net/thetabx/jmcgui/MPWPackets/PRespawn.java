package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PRespawn extends MPWPacket{

	private final static short packetId = 0x09;
	
	// Two way
	private int dimension;
	private byte difficulty;
	private byte creativeMode;
	private short worldHeight;
	private String levelType;
	
	public PRespawn(TCPReader in) throws Exception
	{
		super(packetId);
		this.dimension = in.readInt();
		this.difficulty = in.readByte();
		this.creativeMode = in.readByte();
		this.worldHeight = in.readShort();
		this.levelType = in.readString();
	}
	
	public PRespawn(int dimension, byte difficulty, byte creativeMode, short worldHeight, String levelType)
	{
		super(packetId);
		this.dimension = dimension;
		this.difficulty = difficulty;
		this.creativeMode = creativeMode;
		this.worldHeight = worldHeight;
		this.levelType = levelType;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeInt(dimension);
		out.writeByte(difficulty);
		out.writeByte(creativeMode);
		out.writeShort(worldHeight);
		out.writeString(levelType);
	}

	public int getDimension() {
		return dimension;
	}

	public byte getDifficulty() {
		return difficulty;
	}

	public byte getCreativeMode() {
		return creativeMode;
	}

	public short getWorldHeight() {
		return worldHeight;
	}

	public String getLevelType() {
		return levelType;
	}
}
