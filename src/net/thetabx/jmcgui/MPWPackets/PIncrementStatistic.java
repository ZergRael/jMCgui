package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PIncrementStatistic extends MPWPacket{

	private final static short packetId = 0xC8;
	
	// Server to client
	private int statisticId;
	private byte amount;
	
	public PIncrementStatistic(TCPReader in) throws Exception {
		super(packetId);
		this.statisticId = in.readInt();
		this.amount = in.readByte();
	}
	
	public int getStatisticId()
	{
		return this.statisticId;
	}
	
	public byte getAmount()
	{
		return this.amount;
	}
}
