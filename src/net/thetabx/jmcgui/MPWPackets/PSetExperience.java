package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PSetExperience extends MPWPacket {
    private final static short packetId = 0x2B;
    // Last update 74

    // Server to client
    private float experienceBar;
    private short level;
    private short totalExperience;

    public PSetExperience(TCPReader in) throws Exception {
        super(packetId);
        experienceBar = in.readFloat();
        level = in.readShort();
        totalExperience = in.readShort();
    }

    public float getExperienceBar() {
        return this.experienceBar;
    }

    public short getLevel() {
        return this.level;
    }

    public short getTotalExperience() {
        return this.totalExperience;
    }

    public void gDataMod(McGlobalData gData) {
        gData.setLevel(level);
        gData.setExperience(experienceBar);
    }
}
