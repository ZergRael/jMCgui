package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PSpawnObjectVehicle extends MPWPacket {
    private final static short packetId = 0x17;
    // Last update 74

    // Server to client
    private int eId;
    private byte type;
    private int x;
    private int y;
    private int z;
    private byte pitch;
    private byte yaw;
    private int data;
    private short speedX;
    private short speedY;
    private short speedZ;

    public PSpawnObjectVehicle(TCPReader in) throws Exception {
        super(packetId);
        this.eId = in.readInt();
        this.type = in.readByte();
        this.x = in.readInt();
        this.y = in.readInt();
        this.z = in.readInt();
        pitch = in.readByte();
        yaw = in.readByte();
        data = in.readInt();
        if (data > 0) {
            speedX = in.readShort();
            speedY = in.readShort();
            speedZ = in.readShort();
        }
    }

    public int geteId() {
        return eId;
    }

    public short getSpeedX() {
        return speedX;
    }

    public short getSpeedY() {
        return speedY;
    }

    public short getSpeedZ() {
        return speedZ;
    }

    public byte getType() {
        return type;
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

    public byte getPitch() {
        return pitch;
    }

    public byte getYaw() {
        return yaw;
    }

    public int getData() {
        return data;
    }
}
