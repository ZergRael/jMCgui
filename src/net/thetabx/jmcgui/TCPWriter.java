package net.thetabx.jmcgui;

import java.io.*;
import java.nio.ByteBuffer;

public class TCPWriter {
	
	//private OutputStreamWriter out;
	private BufferedOutputStream out;
	
	//public TCPWriter(OutputStreamWriter out)
	public TCPWriter(BufferedOutputStream out)
	{
		if(out != null)
			this.out = out;
	}
	
	public void writeByte(byte rByte) throws IOException
	{
		//out.write(new char[] { (char)rByte });
		out.write(new byte[] { rByte }, 0, 1);
	}
	
	public void writeByte() throws IOException
	{
		out.write(new byte[1], 0, 1);
	}
	
	public void writeUByte(short rUByte) throws IOException
	{
		//out.write(new char[] { (char)rUByte });
		this.writeByte((byte) (rUByte & 0xFF));
	}
	
	public void writeUByte() throws IOException
	{
		//out.write(new char[] { (char)0 });
		this.writeByte();
	}
	
	public void writeShort(short rShort) throws IOException
	{
		/*char[] c = new char[2];
		c[0] = (char) (rShort >> 8);
		c[1] = (char) (rShort % (1 << 8));
		out.write(c);*/	
		out.write(ByteBuffer.allocate(2).putShort(rShort).array(), 0, 2);
	}
	
	public void writeShort() throws IOException
	{
		//out.write(new char[] { (char)0, (char)0 });
		out.write(new byte[2], 0, 2);
	}
	
	public void writeInt(int rInt) throws IOException
	{
		/*char[] c = new char[4];
		c[0] = (char) (rInt >> 24);
		c[1] = (char) ((rInt % (1 << 24)) >> 16);
		c[2] = (char) ((rInt % (1 << 16)) >> 8);
		c[3] = (char) (rInt % (1 << 8));
		out.write(c);*/
		out.write(ByteBuffer.allocate(4).putInt(rInt).array(), 0, 4);
	}
	
	public void writeInt() throws IOException
	{
		/*char[] c = new char[4];
		c[0] = c[1] = c[2] = c[3] = (char)0;
		out.write(c);*/
		out.write(new byte[4], 0, 4);
	}
	
	public void writeLong(long rLong) throws IOException
	{
		/*char[] c = new char[8];
		c[0] = (char) (rLong >> 56);
		c[1] = (char) ((rLong % (1 << 56)) >> 48);
		c[2] = (char) ((rLong % (1 << 48)) >> 40);
		c[3] = (char) ((rLong % (1 << 40)) >> 32);
		c[4] = (char) ((rLong % (1 << 32)) >> 24);
		c[5] = (char) ((rLong % (1 << 24)) >> 16);
		c[6] = (char) ((rLong % (1 << 16)) >> 8);
		c[7] = (char) (rLong % (1 << 8));
		out.write(c);*/
		out.write(ByteBuffer.allocate(8).putLong(rLong).array(), 0, 8);
	}
	
	public void writeLong() throws IOException
	{
		/*char[] c = new char[8];
		c[0] = c[1] = c[2] = c[3] = c[4] = c[5] = c[6] = c[7] = (char)0;
		out.write(c);*/
		out.write(new byte[8], 0, 8);
	}
	
	public void writeFloat(float rFloat) throws IOException
	{
		//this.writeInt(Float.floatToRawIntBits(rFloat));
		//throw new IOException("writeFloat : can't handle this type");
		out.write(ByteBuffer.allocate(4).putFloat(rFloat).array(), 0, 4);
	}
	
	public void writeFloat() throws IOException
	{
		/*char[] c = new char[4];
		c[0] = c[1] = c[2] = c[3] = (char)0;
		out.write(c);*/
		out.write(new byte[4], 0, 4);
	}
	
	public void writeDouble(double rDouble) throws IOException
	{
		//this.writeLong(Double.doubleToRawLongBits(rDouble));
		//throw new IOException("writeDouble : can't handle this type");
		out.write(ByteBuffer.allocate(8).putDouble(rDouble).array(), 0, 8);
	}
	
	public void writeDouble() throws IOException
	{
		/*char[] c = new char[8];
		c[0] = c[1] = c[2] = c[3] = c[4] = c[5] = c[6] = c[7] = (char)0;
		out.write(c);*/
		out.write(new byte[8], 0, 8);
	}
	
	public void writeString(String rString) throws IOException
	{
		short length = (short) rString.length();
		this.writeShort(length);
		
		out.write(rString.getBytes("UTF-16BE"));
		/*char c[] = new char[length * 2];
		for(short i = 0; i < length * 2; i++)
			c[i] = i % 2 == 1 ? rString.charAt(i/2) : (char)0x00;
			
		out.write(c);*/
	}
	
	public void writeString() throws IOException
	{
		this.writeShort();
	}
	
	public void writeBool(Boolean rBool) throws IOException
	{
		out.write(new byte[] { (rBool ? (byte)1 : (byte)0) }, 0, 1);
	}
	
	public void writeBool() throws IOException
	{
		this.writeByte();
	}
	
	/*public void writeMetadata(Object[] rMetadata) throws IOException
	{
		throw new IOException("writeMetadata : can't handle this type");
	}
	
	public void writeMetadata() throws IOException
	{
		throw new IOException("writeMetadata : can't handle this type");
	}*/
}
