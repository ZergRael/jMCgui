package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PIncrementStatistic extends MPWPacket {
    private final static short packetId = 0xC8;
    // Last update 74

    // Server to client
    private int statisticId;
    private int amount;

    public PIncrementStatistic(TCPReader in) throws Exception {
        super(packetId);
        statisticId = in.readInt();
        amount = in.readInt();
    }

    public int getStatisticId() {
        return statisticId;
    }

    public int getAmount() {
        return amount;
    }
}
