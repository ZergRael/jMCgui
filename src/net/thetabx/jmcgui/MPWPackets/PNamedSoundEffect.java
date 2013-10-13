package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 13/10/13
 * Time: 03:46
 */
public class PNamedSoundEffect extends MPWPacket {
    public static final short packetId = 0x3E;
    // Last update 74

    // Server to client
    private String soundName;
    private int effectPositionX;
    private int effectPositionY;
    private int effectPositionZ;
    private float volume;
    private byte pitch;

    public PNamedSoundEffect(TCPReader in) throws Exception {
        super(packetId);
        soundName = in.readString();
        effectPositionX = in.readInt();
        effectPositionY = in.readInt();
        effectPositionZ = in.readInt();
        volume = in.readFloat();
        pitch = in.readByte();
    }

    public String getSoundName() {
        return soundName;
    }

    public int getEffectPositionX() {
        return effectPositionX;
    }

    public int getEffectPositionY() {
        return effectPositionY;
    }

    public int getEffectPositionZ() {
        return effectPositionZ;
    }

    public float getVolume() {
        return volume;
    }

    public byte getPitch() {
        return pitch;
    }
}
