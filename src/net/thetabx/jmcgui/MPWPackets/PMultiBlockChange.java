package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.DataTypes.MapCoord;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PMultiBlockChange extends MPWPacket{
	private final static short packetId = 0x34;
    // Last update 74
	
	// Server to client
	private int chunkX;
	private int chunkZ;
	private short recordCount;
	private int dataSize;
	private int[] data;
	
	public PMultiBlockChange(TCPReader in) throws Exception {
		super(packetId);
		chunkX = in.readInt();
		chunkZ = in.readInt();
		recordCount = in.readShort();
		dataSize = in.readInt();
		
		data = new int[recordCount];
		for(int i = 0; i < recordCount; i++)
			data[i] = in.readInt();
	}
	
	public int getDataSize() {
		return dataSize;
	}
	
	public void gDataMod(McGlobalData gData) {
		byte[][][] map =  gData.getMapData(new MapCoord(chunkX, chunkZ)).getData();
		
		for(int i = 0; i < recordCount; i++) {
			//byte metaData = (byte) (data[i] & 0xF);
			byte blockId = (byte) ((data[i] >> 1) & 0xFF);
			byte y = (byte) ((data[i] >> 4) & 0xFF);
			byte z = (byte) ((data[i] >> 6) & 0xF);
			byte x = (byte) ((data[i] >> 7) & 0xF);
			
			map[x][z][y] = blockId;
		}
	}
}
