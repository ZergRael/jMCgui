package net.thetabx.jmcgui;

import net.thetabx.jmcgui.MPWObjects.Metadata;
import net.thetabx.jmcgui.MPWObjects.SlotData;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class TCPWriter {

	private BufferedOutputStream out;

	public TCPWriter(BufferedOutputStream out)
	{
		if(out != null)
			this.out = out;
	}
	
	public void writeByte(byte rByte) throws IOException
	{
		out.write(new byte[] { rByte }, 0, 1);
	}
	
	public void writeByte() throws IOException
	{
		out.write(new byte[1], 0, 1);
	}

    public void writeByteArray(byte[] rByteArray) throws IOException
    {
        out.write(rByteArray, 0, rByteArray.length);
    }
	
	public void writeUByte(short rUByte) throws IOException
	{
		this.writeByte((byte) (rUByte & 0xFF));
	}
	
	public void writeUByte() throws IOException
	{
		this.writeByte();
	}
	
	public void writeShort(short rShort) throws IOException
	{
		out.write(ByteBuffer.allocate(2).putShort(rShort).array(), 0, 2);
	}
	
	public void writeShort() throws IOException
	{
		out.write(new byte[2], 0, 2);
	}
	
	public void writeInt(int rInt) throws IOException
	{
		out.write(ByteBuffer.allocate(4).putInt(rInt).array(), 0, 4);
	}
	
	public void writeInt() throws IOException
	{
		out.write(new byte[4], 0, 4);
	}
	
	public void writeLong(long rLong) throws IOException
	{
		out.write(ByteBuffer.allocate(8).putLong(rLong).array(), 0, 8);
	}
	
	public void writeLong() throws IOException
	{
		out.write(new byte[8], 0, 8);
	}
	
	public void writeFloat(float rFloat) throws IOException
	{
		out.write(ByteBuffer.allocate(4).putFloat(rFloat).array(), 0, 4);
	}
	
	public void writeFloat() throws IOException
	{
		out.write(new byte[4], 0, 4);
	}
	
	public void writeDouble(double rDouble) throws IOException
	{
		out.write(ByteBuffer.allocate(8).putDouble(rDouble).array(), 0, 8);
	}
	
	public void writeDouble() throws IOException
	{
		out.write(new byte[8], 0, 8);
	}
	
	public void writeString(String rString) throws IOException
	{
		short length = (short) rString.length();
		this.writeShort(length);
		
		out.write(rString.getBytes("UTF-16BE"));
	}
	
	public void writeString() throws IOException
	{
		this.writeShort();
	}
	
	public void writeBool(Boolean rBool) throws IOException
	{
		out.write(new byte[] { (rBool ? (byte)1 : (byte)0) }, 0, 1);
	}

    public void writeSlotData(SlotData slotData) throws IOException
    {
        this.writeShort(slotData.getItemBlockId());
        if(slotData.getItemBlockId() != -1)
        {
            this.writeByte(slotData.getItemCount());
            this.writeShort(slotData.getItemDamage());
            this.writeShort(slotData.getNbtDataLength());
            if(slotData.getNbtDataLength() != -1)
            {
                this.writeByteArray(slotData.getNbtData());
            }
        }
    }
	
	public void writeBool() throws IOException
	{
		this.writeByte();
	}
	
	public void writeMetadata(ArrayList<Metadata> rMetadataList) throws IOException
	{
		for(Metadata metaData : rMetadataList)
        {
            //TODO writeMetadata()
        }
        this.writeUByte((short) 127);
        throw new IOException("writeMetadata : can't handle this type");

	}
}
