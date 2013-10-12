package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PUpdateHealth extends MPWPacket{

	private final static short packetId = 0x08;
	
	// Server to client
	private float health;
	private short food;
	private float foodSaturation;
	
	public PUpdateHealth(TCPReader in) throws Exception {
		super(packetId);
		this.health = in.readFloat();
		this.food = in.readShort();
		this.foodSaturation = in.readFloat();
	}

	public float getHealth()
	{
		return this.health;
	}
	
	public short getFood()
	{
		return this.food;
	}
	
	public float getFoodSaturation()
	{
		return this.foodSaturation;
	}
	
	public void gDataMod(McGlobalData gData)
	{
		gData.setHealth(health);
		gData.setHunger(food);
	}
}
