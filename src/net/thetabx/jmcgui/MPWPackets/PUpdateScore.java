package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 11/10/13
 * Time: 01:05
 */
public class PUpdateScore extends MPWPacket {
    public static final short packetId = 0xCF;
    // Last update 74

    // Server to client
    private String itemName;
    private byte updateRemove;
    private String scoreName;
    private int value;

    public PUpdateScore(TCPReader in) throws Exception {
        super(packetId);
        itemName = in.readString();
        updateRemove = in.readByte();
        scoreName = in.readString();
        value = in.readInt();
    }

    public String getItemName() {
        return itemName;
    }

    public byte getUpdateRemove() {
        return updateRemove;
    }

    public String getScoreName() {
        return scoreName;
    }

    public int getValue() {
        return value;
    }
}
