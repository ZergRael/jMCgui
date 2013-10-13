package net.thetabx.jmcgui.MPWPackets;


import net.thetabx.jmcgui.MPWObjects.Metadata;
import net.thetabx.jmcgui.TCPReader;

import java.util.ArrayList;

public class PEntityMetadata extends MPWPacket {
    private final static short packetId = 0x28;
    // Last update 74

    // Server to client
    private int eId;
    private ArrayList<Metadata> metadata;

    public PEntityMetadata(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        metadata = in.readMetadata();
    }

    public int geteId() {
        return eId;
    }

    public ArrayList<Metadata> getMetadata() {
        return metadata;
    }
}
