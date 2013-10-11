package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PTimeUpdate extends MPWPacket{

	private final static short packetId = 0x04;
	
	// Server to client
    private long ageOfTheWorld;
	private long timeOfDay;
	
	public PTimeUpdate(TCPReader in) throws Exception {
		super(packetId);
        ageOfTheWorld = in.readLong();
		timeOfDay = in.readLong();
	}

	public long getAgeOfTheWorld() {
		return ageOfTheWorld;
	}

    public long getTimeOfDay() {
        return timeOfDay;
    }
	
	public void gDataMod(McGlobalData gData)
	{
		gData.setTimeTicks(timeOfDay);
	}
}
