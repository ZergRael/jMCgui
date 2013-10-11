package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PChangeGameState extends MPWPacket{

	private final static short packetId = 0x46;
	
	// Server to client
	private byte reason;
	private byte gameMode;
	
	public PChangeGameState(TCPReader in) throws Exception {
		super(packetId);
		this.reason = in.readByte();
		this.gameMode = in.readByte();
	}

	public byte getReason() {
		return reason;
	}

	public byte getGameMode() {
		return gameMode;
	}
}
