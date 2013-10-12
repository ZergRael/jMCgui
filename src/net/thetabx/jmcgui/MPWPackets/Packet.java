
package net.thetabx.jmcgui.MPWPackets;

public enum Packet {
	KeepAlive(0x00, PKeepAlive.class),
	LoginRequest(0x01, PLoginRequest.class),
	Handshake(0x02, PHandshake.class),
	ChatMessage(0x03, PChatMessage.class),
	TimeUpdate(0x04, PTimeUpdate.class),
	EntityEquipment(0x05, PEntityEquipment.class),
	SpawnPosition(0x06, PSpawnPosition.class),
	UseEntity(0x07, PUseEntity.class),
	UpdateHealth(0x08, PUpdateHealth.class),
	Respawn(0x09, PRespawn.class),
	Player(0x0A, PPlayer.class),
	PlayerPosition(0x0B, PPlayerPosition.class),
	PlayerLook(0x0C, PPlayerLook.class),
	PlayerPositionLook(0x0D, PPlayerPositionLook.class),
	PlayerDigging(0x0E, PPlayerDigging.class),
	PlayerBlockPlacement(0x0F, PPlayerBlockPlacement.class),
	HeldItemChange(0x10, PHeldItemChange.class),
	UseBed(0x11, PUseBed.class),
	Animation(0x12, PAnimation.class),
	EntityAction(0x13, PEntityAction.class),
	SpawnNamedEntity(0x14, PSpawnNamedEntity.class),
	SpawnDroppedItem(0x15, PSpawnDroppedItem.class),
	CollectItem(0x16, PCollectItem.class),
	SpawnObjectVehicle(0x17, PSpawnObjectVehicle.class),
	SpawnMob(0x18, PSpawnMob.class),
	SpawnPainting(0x19, PSpawnPainting.class),
	SpawnExperienceOrb(0x1A, PSpawnExperienceOrb.class),
	EntityVelocity(0x1C, PEntityVelocity.class),
	DestroyEntity(0x1D, PDestroyEntity.class),
	Entity(0x1E, PEntity.class),
	EntityRelativeMove(0x1F, PEntityRelativeMove.class),
	EntityLook(0x20, PEntityLook.class),
	EntityLookRelativeMove(0x21, PEntityLookRelativeMove.class),
	EntityTeleport(0x22, PEntityTeleport.class),
	EntityHeadLook(0x23, PEntityHeadLook.class),
	EntityStatus(0x26, PEntityStatus.class),
	AttachEntity(0x27, PAttachEntity.class),
	EntityMetadata(0x28, PEntityMetadata.class),
	EntityEffect(0x29, PEntityEffect.class),
	RemoveEntityEffect(0x2A, PRemoveEntityEffect.class),
	SetExperience(0x2B, PSetExperience.class),
    EntityProperties(0x2C, PEntityProperties.class),
	MapColumnAllocation(0x32, PMapColumnAllocation.class),
	ChunkData(0x33, PChunkData.class),
	MultiBlockChange(0x34, PMultiBlockChange.class),
	BlockChange(0x35, PBlockChange.class),
	BlockAction(0x36, PBlockAction.class),
    MapChunkBulk(0x38, PMapChunkBulk.class),
	Explosion(0x3C, PExplosion.class),
	SoundParticleEffect(0x3D, PSoundParticleEffect.class),
	GameStateChange(0x46, PGameStateChange.class),
	Thunderbolt(0x47, PThunderbolt.class),
	OpenWindow(0x64, POpenWindow.class),
	CloseWindow(0x65, PCloseWindow.class),
	ClickWindow(0x66, PClickWindow.class),
	SetSlot(0x67, PSetSlot.class),
	SetWindowItems(0x68, PSetWindowItems.class),
	UpdateWindowProperty(0x69, PUpdateWindowProperty.class),
	ConfirmTransaction(0x6A, PConfirmTransaction.class),
	CreativeInventoryAction(0x6B, PCreativeInventoryAction.class),
	EnchantItem(0x6C, PEnchantItem.class),
	UpdateSign(0x82, PUpdateSign.class),
	ItemData(0x83, PItemData.class),
	UpdateTileEntity(0x84, PUpdateTileEntity.class),
	IncrementStatistic(0xC8, PIncrementStatistic.class),
	PlayerListItem(0xC9, PPlayerListItem.class),
	PlayerAbilities(0xCA, PPlayerAbilities.class),
    ClientStatuses(0xCD, PClientStatuses.class),
    ScoreboardObjective(0xCE, PScoreboardObjective.class),
    UpdateScore(0xCF, PUpdateScore.class),
    DisplayScoreboard(0xD0, PDisplayScoreboard.class),
    Teams(0xD1, PTeams.class),
	PluginMessage(0xFA, PPluginMessage.class),
    EncryptionKeyRequest(0xFD, PEncryptionKeyRequest.class),
	ServerListPing(0xFE, PServerListPing.class),
	DisconnectKick(0xFF, PDisconnectKick.class);
	
	private int hexCode;
	private Class<MPWPacket> c;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Packet(int hexCode, Class c)
	{
		this.hexCode = hexCode;
		this.c = c;
	}
	
	public int getHexCode()
	{
		return hexCode;
	}
	
	public Class<MPWPacket> getPacketClass()
	{
		return c;
	}
	
	public static Class<MPWPacket> fromCode(int hexCode)
	{
		Packet[] p = Packet.values();
		for(int i = 0; i < p.length; i++)
		{
			if(p[i].getHexCode() == hexCode)
				return p[i].getPacketClass();
		}

		return null;
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
