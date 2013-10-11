package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 11/10/13
 * Time: 01:02
 */
public class PDisplayScoreboard extends MPWPacket {
    public static final short packetId = 0xD0;

    // Server to client
    private byte position;
    private String scoreName;


    public PDisplayScoreboard(TCPReader in) throws Exception {
        super(packetId);
        position = in.readByte();
        scoreName = in.readString();
    }

    public byte getPosition() {
        return position;
    }

    public String getScoreName() {
        return scoreName;
    }
}
