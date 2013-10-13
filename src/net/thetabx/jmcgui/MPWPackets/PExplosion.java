package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PExplosion extends MPWPacket {
    private final static short packetId = 0x3C;
    // Last update 74

    // Server to client
    private double x;
    private double y;
    private double z;
    private float radius;
    private int recordCount;
    private byte[][] records;
    private float playerMotionX;
    private float playerMotionY;
    private float playerMotionZ;

    public PExplosion(TCPReader in) throws Exception {
        super(packetId);
        x = in.readDouble();
        y = in.readDouble();
        z = in.readDouble();
        radius = in.readFloat();
        recordCount = in.readInt();
        records = new byte[recordCount][];
        for (int i = 0; i < recordCount; i++) {
            this.records[i] = new byte[3];
            records[i][0] = in.readByte();
            records[i][1] = in.readByte();
            records[i][2] = in.readByte();
        }
        playerMotionX = in.readFloat();
        playerMotionY = in.readFloat();
        playerMotionZ = in.readFloat();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getRadius() {
        return radius;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public byte[][] getRecords() {
        return records;
    }

    public float getPlayerMotionX() {
        return playerMotionX;
    }

    public float getPlayerMotionY() {
        return playerMotionY;
    }

    public float getPlayerMotionZ() {
        return playerMotionZ;
    }
}
