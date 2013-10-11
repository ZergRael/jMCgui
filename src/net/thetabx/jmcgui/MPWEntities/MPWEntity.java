package net.thetabx.jmcgui.MPWEntities;

abstract public class MPWEntity {

	private int eId;
	private EntityType type;
	private double x, y, z;
	private String name;
	
	public MPWEntity(int eId, EntityType type, double x, double y, double z, String name)
	{
		this.eId = eId;
		this.type = type;
		this.name = name;
		this.setXYZ(x, y, z);
	}
	
	public int getEId()
	{
		return eId;
	}
	
	public double getX()
	{
		return x;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public void setZ(double z)
	{
		this.z = z;
	}
	
	public void setXYZ(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public EntityType getType() {
		return type;
	}
}
