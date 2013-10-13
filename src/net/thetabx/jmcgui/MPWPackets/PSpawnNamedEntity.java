package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWEntities.EPlayer;
import net.thetabx.jmcgui.MPWObjects.Metadata;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

import java.util.ArrayList;

public class PSpawnNamedEntity extends MPWPacket {
    private final static short packetId = 0x14;
    // Last update 74

    // Server to client
    private int eId;
    private String playerName;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte pitch;
    private short currentItem;
    private ArrayList<Metadata> metadata;

    public PSpawnNamedEntity(TCPReader in) throws Exception {
        super(packetId);
        eId = in.readInt();
        playerName = in.readString();
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        yaw = in.readByte();
        pitch = in.readByte();
        currentItem = in.readShort();
        metadata = in.readMetadata();
    }

    public short getCurrentItem() {
        return currentItem;
    }

    public int geteId() {
        return eId;
    }

    public byte getPitch() {
        return pitch;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getX() {
        return (double) x / 32;
    }

    public double getY() {
        return (double) y / 32;
    }

    public byte getYaw() {
        return yaw;
    }

    public double getZ() {
        return (double) z / 32;
    }

    public ArrayList<Metadata> getMetadata() {
        return metadata;
    }

    public void gDataMod(McGlobalData gData) {
        synchronized (gData.entities) {
            gData.setEntity(this.geteId(), new EPlayer(this.geteId(), this.getX(), this.getY(), this.getZ(), playerName));
        }
    }
}
