package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PCollectItem extends MPWPacket {
    private final static short packetId = 0x16;
    // Last update 74

    // Server to client
    private int collectedEId;
    private int collectorEId;

    public PCollectItem(TCPReader in) throws Exception {
        super(packetId);
        collectedEId = in.readInt();
        collectorEId = in.readInt();
    }

    public int getCollectedEId() {
        return collectedEId;
    }

    public int getCollectorEId() {
        return collectorEId;
    }
}
