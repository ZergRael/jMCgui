package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PAnimation extends MPWPacket{
	private final static short packetId = 0x12;
    // Last update 74
	
	// 2 Way
	private int eId;
	private byte animation;
	
	// NO_ANIMATION = 0, SWING_ARM = 1, DAMAGE_ANIMATION = 2, LEAVE_BED = 3, EAT_FOOD = 5, UNKNOWN = 102, CROUCH = 104, UNCROUCH = 105;
	
	public PAnimation(TCPReader in) throws Exception {
		super(packetId);
		eId = in.readInt();
		animation = in.readByte();
	}
	
	public PAnimation(int eId, byte animation)
	{
		super(packetId);
		this.eId = eId;
		this.animation = animation;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
		out.writeInt(eId);
		out.writeByte(animation);
	}
	
	public int geteId()
	{
		return eId;
	}
	
	public byte getAnimation()
	{
		return animation;
	}
}
