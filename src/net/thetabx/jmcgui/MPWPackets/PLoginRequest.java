package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PLoginRequest extends MPWPacket{

	private final static short packetId = 0x01;

	// Server to client
	private int entityId;
	private String levelType;
	private byte gameMode;
	private byte dimension;
	private byte difficulty;
	private short maxPlayers;
	
	public PLoginRequest(TCPReader in) throws Exception {
		super(packetId);
		this.entityId = in.readInt();
		this.levelType = in.readString();
		this.gameMode = in.readByte();
		this.dimension = in.readByte();
		this.difficulty = in.readByte();
		in.readByte();
		this.maxPlayers = in.readUByte();
	}
	
	public void gDataMod(McGlobalData gData)
	{
		gData.setPlayerEntityId(this.entityId);
	}

	public byte getDifficulty() {
		return difficulty;
	}

	public int getDimension() {
		return dimension;
	}

	public String getLevelType() {
		return levelType;
	}

	public short getMaxPlayers() {
		return maxPlayers;
	}

	public int getGameMode() {
		return gameMode;
	}
}
