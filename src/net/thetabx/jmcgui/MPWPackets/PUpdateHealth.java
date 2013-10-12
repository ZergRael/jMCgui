package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PUpdateHealth extends MPWPacket{
	private final static short packetId = 0x08;
    // Last update 74
	
	// Server to client
	private float health;
	private short food;
	private float foodSaturation;
	
	public PUpdateHealth(TCPReader in) throws Exception {
		super(packetId);
		health = in.readFloat();
		food = in.readShort();
		foodSaturation = in.readFloat();
        // TODO gData.foodSaturation
	}

	public float getHealth()
	{
		return health;
	}
	
	public short getFood()
	{
		return food;
	}
	
	public float getFoodSaturation()
	{
		return foodSaturation;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		gData.setHealth(health);
		gData.setHunger(food);
	}
}
