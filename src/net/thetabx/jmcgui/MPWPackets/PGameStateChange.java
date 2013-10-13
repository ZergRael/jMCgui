package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PGameStateChange extends MPWPacket {
    private final static short packetId = 0x46;
    // Last update 74

    // Server to client
    private byte reason;
    private byte gameMode;

    public PGameStateChange(TCPReader in) throws Exception {
        super(packetId);
        reason = in.readByte();
        gameMode = in.readByte();
    }

    public byte getGameMode() {
        return gameMode;
    }

    public byte getReason() {
        return reason;
    }
}
