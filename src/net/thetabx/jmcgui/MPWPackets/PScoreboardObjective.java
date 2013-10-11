package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 11/10/13
 * Time: 00:59
 */
public class PScoreboardObjective extends MPWPacket {
    public static final short packetId = 0xCE;

    // Server to client
    private String objectiveName;
    private String objectiveValue;
    private byte createRemove;


    public PScoreboardObjective(TCPReader in) throws Exception {
        super(packetId);
        objectiveName = in.readString();
        objectiveValue = in.readString();
        createRemove = in.readByte();
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public String getObjectiveValue() {
        return objectiveValue;
    }

    public byte getCreateRemove() {
        return createRemove;
    }
}
