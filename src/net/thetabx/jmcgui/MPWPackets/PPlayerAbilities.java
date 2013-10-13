package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PPlayerAbilities extends MPWPacket {
    private final static short packetId = 0xCA;
    // Last update 74

    // Two way
    private byte flags;
    private float flyingSpeed;
    private float walkingSpeed;

    public PPlayerAbilities(TCPReader in) throws Exception {
        super(packetId);
        flags = in.readByte();
        flyingSpeed = in.readInt() / 250;
        walkingSpeed = in.readInt() / 250;
    }

    public PPlayerAbilities(byte flags, float flyingSpeed, float walkingSpeed) {
        super(packetId);
        this.flags = flags;
        this.flyingSpeed = flyingSpeed;
        this.walkingSpeed = walkingSpeed;
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeByte(flags);
        out.writeInt((int) (flyingSpeed * 250));
        out.writeInt((int) (walkingSpeed * 250));
    }

    public byte getFlags() {
        return flags;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }
}
