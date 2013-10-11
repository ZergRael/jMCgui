package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PExplosion extends MPWPacket{

	private final static short packetId = 0x3C;
	
	// Server to client
	private double x;
	private double y;
	private double z;
	private float radius;
	private int recordCount;
	private byte[][] records;
	
	public PExplosion(TCPReader in) throws Exception {
		super(packetId);
		this.x = in.readDouble();
		this.y = in.readDouble();
		this.z = in.readDouble();
		this.radius = in.readFloat();
		this.recordCount = in.readInt();
		this.records = new byte[recordCount][];
		for(int i = 0; i < recordCount; i++) {
			this.records[i] = new byte[3];
			records[i][0] = in.readByte();
			records[i][1] = in.readByte();
			records[i][2] = in.readByte();
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public float getRadius() {
		return radius;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public byte[][] getRecords() {
		return records;
	}
}
