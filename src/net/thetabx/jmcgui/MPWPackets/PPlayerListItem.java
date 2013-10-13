package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PPlayerListItem extends MPWPacket {
    private final static short packetId = 0xC9;
    // Last update 74

    // Server to client
    private String playerName;
    private Boolean online;
    private short ping;

    public PPlayerListItem(TCPReader in) throws Exception {
        super(packetId);
        playerName = in.readString();
        online = in.readBool();
        ping = in.readShort();
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
}
