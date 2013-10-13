package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PRespawn extends MPWPacket {
    private final static short packetId = 0x09;
    // Last update 74

    // Server to client
    private int dimension;
    private byte difficulty;
    private byte creativeMode;
    private short worldHeight;
    private String levelType;

    public PRespawn(TCPReader in) throws Exception {
        super(packetId);
        dimension = in.readInt();
        difficulty = in.readByte();
        creativeMode = in.readByte();
        worldHeight = in.readShort();
        levelType = in.readString();
    }

    public int getDimension() {
        return dimension;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public byte getCreativeMode() {
        return creativeMode;
    }

    public short getWorldHeight() {
        return worldHeight;
    }

    public String getLevelType() {
        return levelType;
    }
}
