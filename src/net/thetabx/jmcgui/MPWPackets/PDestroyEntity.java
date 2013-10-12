package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;

public class PDestroyEntity extends MPWPacket{
	private final static short packetId = 0x1D;
    // Last update 74
	
	// Server to client
	private byte entityCount;
    private int[] entitiesIds;
	
	public PDestroyEntity(TCPReader in) throws Exception {
		super(packetId);
        entityCount = in.readByte();
        entitiesIds = new int[entityCount];
        for(byte i = 0; i < entityCount; i++)
            entitiesIds[i] = in.readInt();
	}

    public byte getEntityCount() {
        return entityCount;
    }

    public int[] getEntitiesIds() {
        return entitiesIds;
    }

    public void gDataMod(McGlobalData gData)
	{
		synchronized(gData)
		{
			for(int entityId : entitiesIds)
                gData.removeEntity(entityId);
		}
	}
}
