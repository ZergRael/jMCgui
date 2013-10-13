package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnPainting extends MPWPacket {
    private final static short packetId = 0x19;
    // Last update 74

    // Server to client
    private int eId;
    private String title;
    private int x;
    private int y;
    private int z;
    private int direction;

    public PSpawnPainting(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        title = in.readString();
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        direction = in.readInt();
    }

    public int getDirection() {
        return direction;
    }

    public int geteId() {
        return eId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getTitle() {
        return title;
    }
}
