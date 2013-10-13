package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 13/10/13
 * Time: 03:43
 */
public class PParticle extends MPWPacket {
    public static final short packetId = 0x3F;
    // Last update 74

    // Server to client
    private String particleName;
    private float x;
    private float y;
    private float z;
    private float offsetX;
    private float offsetY;
    private float offsetZ;
    private float particleSpeed;
    private int numberOfParticles;

    public PParticle(TCPReader in) throws Exception {
        super(packetId);
        particleName = in.readString();
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        offsetX = in.readFloat();
        offsetY = in.readFloat();
        offsetZ = in.readFloat();
        particleSpeed = in.readFloat();
        numberOfParticles = in.readInt();
    }

    public String getParticleName() {
        return particleName;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public float getOffsetZ() {
        return offsetZ;
    }

    public float getParticleSpeed() {
        return particleSpeed;
    }

    public int getNumberOfParticles() {
        return numberOfParticles;
    }
}
