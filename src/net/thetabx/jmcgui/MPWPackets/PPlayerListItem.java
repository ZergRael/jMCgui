package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PPlayerListItem extends MPWPacket{

	private final static short packetId = 0xC9;
	
	// Server to client
	private String playerName;
	private Boolean online;
	private short ping;
	
	public PPlayerListItem(TCPReader in) throws Exception {
		super(packetId);
		this.playerName = in.readString();
		this.online = in.readBool();
		this.ping = in.readShort();
	}

	public Boolean getOnline() {
		return online;
	}

	public short getPing() {
		return ping;
	}

	public String getPlayerName() {
		return playerName;
	}

	public short getPacketId() {
		return packetId;
	}
}
