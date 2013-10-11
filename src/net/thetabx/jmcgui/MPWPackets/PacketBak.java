
package net.thetabx.jmcgui.MPWPackets;

public enum PacketBak {
	KeepAlive(0x00), LoginRequest(0x01), Handshake(0x02), ChatMessage(0x03), TimeUpdate(0x04), EntityEquipment(0x05), SpawnPosition(0x06), UseEntity(0x07), 
	UpdateHealth(0x08), Respawn(0x09), Player(0x0A), PlayerPosition(0x0B), PlayerLook(0x0C), PlayerPositionLook(0x0D), PlayerDigging(0x0E), PlayerBlockPlacement(0x0F),
	HeldItemChange(0x10), UseBed(0x11), Animation(0x12), EntityAction(0x13), SpawnNamedEntity(0x14), SpawnDroppedItem(0x15), CollectItem(0x16), SpawnObjectVehicle(0x17),
	SpawnMob(0x18), SpawnPainting(0x19), SpawnExperienceOrb(0x1A), EntityVelocity(0x1C), DestroyEntity(0x1D), Entity(0x1E), EntityRelativeMove(0x1F), EntityLook(0x20),
	EntityLookRelativeMove(0x21), EntityTeleport(0x22), EntityHeadLook(0x23), EntityStatus(0x26), AttachEntity(0x27), EntityMetadata(0x28), EntityEffect(0x29),
	RemoveEntityEffect(0x2A), SetExperience(0x2B), MapColumnAllocation(0x32), MapChunks(0x33), MultiBlockChange(0x34), BlockChange(0x35), BlockAction(0x36), Explosion(0x3C),
	SoundParticleEffect(0x3D), GameStateChange(0x46), Thunderbolt(0x47), OpenWindow(0x64), CloseWindow(0x65), ClickWindow(0x66), SetSlot(0x67), SetWindowItems(0x68),
	UpdateWindowProperty(0x69), ConfirmTransaction(0x6A), CreativeInventoryAction(0x6B), EnchantItem(0x6C), UpdateSign(0x82), ItemData(0x83), UpdateTileEntity(0x84),
	IncrementStatistic(0xC8), PlayerListItem(0xC9), PlayerAbilities(0xCA), PluginMessage(0xFA), ServerPingList(0xFE), DisconnectKick(0xFF);
	
	private int hexCode;
	
	private PacketBak(int hexCode)
	{
		this.hexCode = hexCode;
	}
	
	public int getHexCode()
	{
		return hexCode;
	}

	public static String getName(int hexCode)
	{
		Packet[] p = Packet.values();
		for(int i = 0; i < p.length; i++)
		{
			if(p[i].getHexCode() == hexCode)
				return p[i].name();
		}

		return null;
	}
}
