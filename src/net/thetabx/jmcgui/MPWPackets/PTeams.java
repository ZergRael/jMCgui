package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 11/10/13
 * Time: 00:44
 */
public class PTeams extends MPWPacket {
    public static final short packetId = 0xD1;

    // Two way
    private String teamName;
    private byte mode;
    private String teamDisplayName;
    private String teamPrefix;
    private String teamSuffix;
    private byte friendlyFire;
    private short playerCount;
    private String[] players;

    public PTeams(TCPReader in) throws Exception {
        super(packetId);
        teamName = in.readString();
        mode = in.readByte();
        if(mode == 1)
            return;

        if(mode == 0 || mode == 2)
        {
            teamDisplayName = in.readString();
            teamPrefix = in.readString();
            teamSuffix = in.readString();
            friendlyFire = in.readByte();
        }

        if(mode == 0 || mode > 2)
        {
            playerCount = in.readShort();
            players = in.readStringArray(playerCount);
        }
    }

    public PTeams(String teamName, byte mode, String teamDisplayName, String teamPrefix, String teamSuffix, byte friendlyFire, short playerCount, String[] players)
    {
        super(packetId);
        this.teamName = teamName;
        this.mode = mode;
        this.teamDisplayName = teamDisplayName;
        this.teamPrefix = teamPrefix;
        this.teamSuffix = teamSuffix;
        this.friendlyFire = friendlyFire;
        this.playerCount = playerCount;
        this.players = players;
    }

    public void send(TCPWriter out) throws Exception
    {
        out.writeUByte(packetId);
        out.writeString(teamName);
    }
}
