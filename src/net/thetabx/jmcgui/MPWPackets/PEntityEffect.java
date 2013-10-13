package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PEntityEffect extends MPWPacket {
    private final static short packetId = 0x29;
    // Last update 74

    // Server to client
    private int eId;
    private byte effectId;
    private byte amplifier;
    private short duration;

    public PEntityEffect(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        effectId = in.readByte();
        amplifier = in.readByte();
        duration = in.readShort();
    }

    public int geteId() {
        return eId;
    }

    public byte getEffectId() {
        return effectId;
    }

    public byte getAmplifier() {
        return amplifier;
    }

    public short getDuration() {
        return duration;
    }
}
