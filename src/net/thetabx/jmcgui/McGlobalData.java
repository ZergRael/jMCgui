package net.thetabx.jmcgui;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import net.thetabx.jmcgui.DataTypes.MapCoord;
import net.thetabx.jmcgui.DataTypes.MapData;
import net.thetabx.jmcgui.MPWEntities.MPWEntity;
public class McGlobalData {

	private String nickname;
	private String address;
	private int port;
	private boolean run;
	private StopReason stopReason;
	private String sessionId;
	//private String connectionHash;
    private String serverId;
	private int playerEntityId;
	private long timeTicks;
	private float health;
	private short hunger;
	private short level;
	private float experience;
	private Hashtable<Integer, MPWEntity> entities = new Hashtable<Integer, MPWEntity>();
	public final ArrayList<String> receivedChatMessages = new ArrayList<String>();
	public final ArrayList<String> toSendChatMessages = new ArrayList<String>();
	private Hashtable<MapCoord, MapData> mapData = new Hashtable<MapCoord, MapData>();
	
	public enum StopReason {
		OK, DISCONNECT, KICK, ERROR;
	}
	
	// Nickname
	public String getNickname() {
		return nickname != null ? nickname : "RandomBobby";
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	// Address
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	// Port
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	// Session Id
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	// Connection hash
	/*public String getConnectionHash() {
		return connectionHash;
	}
	public void setConnectionHash(String connectionHash) {
		this.connectionHash = connectionHash;
	}  */
    // Server Id
    public String getServerId() {
        return serverId;
    }
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
    // Player entity Id
	public int getPlayerEntityId() {
		return playerEntityId;
	}
	public void setPlayerEntityId(int playerEntityId) {
		this.playerEntityId = playerEntityId;
	}
	// Entity
	public void setEntity(int eId, MPWEntity e)
	{
		this.entities.put(eId, e);
	}
	public MPWEntity getEntity(int eId)
	{
		return this.entities.get(eId);
	}
	public void removeEntity(int eId)
	{
		this.entities.remove(eId);
	}
	public int getEntitiesSize()
	{
		return this.entities.size();
	}
	public Enumeration<Integer> getEntitiesKeys()
	{
		return this.entities.keys();
	}
	// Running
	public void setRunning(boolean run)
	{
		this.run = run;
	}
	public void stop(StopReason reason, String data)
	{
		this.run = false;
		this.stopReason = reason;
		System.out.println("Stop : " + reason.toString() + " | " + data);
	}
	public StopReason getStopReason()
	{
		return this.stopReason;
	}
	public boolean isRunning()
	{
		return this.run;
	}
	// Time ticks
	public void setTimeTicks(long timeTicks)
	{
		this.timeTicks = timeTicks;
	}
	public long getTimeTicks()
	{
		return this.timeTicks;
	}
	// Chat messages
    public boolean hasReceivedMessagesWaiting()
    {
        return !receivedChatMessages.isEmpty();
    }
	public void addReceivedMessage(String str)
	{
		receivedChatMessages.add(str);
	}
	public String getNextAwaitingMessage()
	{
		return (receivedChatMessages.isEmpty() ? null : receivedChatMessages.remove(0));
	}
	public void addToSendMessage(String message) {
		toSendChatMessages.add(message);
	}
	public String getToSendChatMessage()
	{
		return (toSendChatMessages.isEmpty() ? null : toSendChatMessages.remove(0));
	}
	// Health
	public void setHealth(float health) {
		this.health = health;
	}
	public float getHealth() {
		return this.health;
	}
	// Hunger
	public void setHunger(short hunger) {
		this.hunger = hunger;
	}
	public short getHunger() {
		return this.hunger;
	}
	// Level
	public void setLevel(short level) {
		this.level = level;
	}
	public short getLevel() {
		return this.level;
	}
	// Experience
	public void setExperience(float experience) {
		this.experience = experience;
	}
	public float getExperience() {
		return this.experience;
	}
	// MapData
	public void allocMapData(MapCoord coord) {
		mapData.put(coord, new MapData());
	}
	public void removeMapData(MapCoord coord) {
		mapData.remove(coord);
	}
	public void clearMapData() {
		mapData.clear();
	}
	public boolean containsMapDataKey(MapCoord coord) {
		return mapData.containsKey(coord);
	}
	public boolean setMapData(MapCoord coord, MapData data) {
		if(!mapData.containsKey(coord))
			return false;
		
		mapData.put(coord, data);
		return true;
	}
	public MapData getMapData(MapCoord coord) {
		return mapData.get(coord);
	}
}
