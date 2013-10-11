package net.thetabx.jmcgui;

import net.thetabx.jmcgui.MPWPackets.McMetadata;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class TCPReader {
	
	//private BufferedReader in = null;
	private BufferedInputStream in = null;
	
	//public TCPReader(BufferedReader in)
	public TCPReader(BufferedInputStream in)
	{
		if(in != null)
			this.in = in;
	}
	
	public byte readByte() throws Exception
	{
		byte c[] = new byte[1];
		if(in.read(c, 0, 1) == 1)
			return c[0];
		else
			throw new Exception("Byte : buffer is empty");
	}

    public byte[] readByteArray(int len) throws Exception
    {
        byte c[] = new byte[len];
        if(in.read(c, 0, len) == len)
            return c;
        else
            throw new Exception("ByteArray : buffer is empty");
    }
	
	public short readUByte() throws Exception
	{
		byte c[] = new byte[1];
		if(in.read(c, 0, 1) == 1)
			return (short) (c[0] & 0xFF);
		else
			throw new Exception("UByte : buffer is empty");
	}
	
	public short readShort() throws Exception
	{
		byte c[] = new byte[2];
		if(in.read(c, 0, 2) == 2)
			return ByteBuffer.wrap(c).getShort();
			//return (short) (c[0] << 8 | c[1]);
		else
			throw new Exception("Short : buffer is empty");
	}
	
	public int readUShort() throws Exception
	{
		byte c[] = new byte[2];
		if(in.read(c, 0, 2) == 2)
			return (int) (ByteBuffer.wrap(c).getShort() & 0xFFFF);
		else
			throw new Exception("UShort : buffer is empty");
	}
	
	public int readInt() throws Exception
	{
		byte c[] = new byte[4];
		if(in.read(c, 0, 4) == 4)
			return ByteBuffer.wrap(c).getInt();
			//return (int) (c[0] << 24 | c[1] << 16 | c[2] << 8 | c[3]);
		else
			throw new Exception("Int : buffer is empty");
	}
	
	public long readLong() throws Exception
	{
		byte c[] = new byte[8];
		if(in.read(c, 0, 8) == 8)
			return ByteBuffer.wrap(c).getLong();
			//return (long) (c[0] << 56 | c[1] << 48 | c[2] << 40 | c[3] << 32 | c[4] << 24 | c[5] << 16 | c[6] << 8 | c[7]);
		else
			throw new Exception("Long : buffer is empty");
	}
	
	public float readFloat() throws Exception
	{
		byte c[] = new byte[4];
		if(in.read(c, 0, 4) == 4)
			return ByteBuffer.wrap(c).getFloat();
		/*{
			byte[] bytes = new byte[4];
			for(int i = 0; i < 4; i++)
				bytes[i] = (byte) c[i];
			ByteBuffer buf = ByteBuffer.wrap(bytes);  
		    return buf.getFloat();  
		}*/
		else
			throw new Exception("Float : buffer is empty");
	}
	
	public double readDouble() throws Exception
	{
		byte c[] = new byte[8];
		if(in.read(c, 0, 8) == 8)
			return ByteBuffer.wrap(c).getDouble();
		/*{
			byte[] bytes = new byte[8];
			for(int i = 0; i < 8; i++)
				bytes[i] = (byte) c[i];
			ByteBuffer buf = ByteBuffer.wrap(bytes);  
		    return buf.getDouble();  
		}*/
		else
			throw new Exception("Double : buffer is empty");
	}
	
	public String readString() throws Exception
	{
		short length = (short) (this.readShort() * 2);
		
		byte c[] = new byte[length];
		if(in.read(c, 0, length) == length)
		{
			/*String str = "";
			
			for(int i = 0; i < length; i++)
				str += i % 2 == 1 ? (char)c[i] : "";
			return new String(str);*/
			return new String(c, "UTF-16BE");
			
		}
		else
			throw new Exception("String : buffer is empty");
	}

    public String[] readStringArray(short len) throws Exception
    {
        if(len == 0)
            return new String[0];
        String[] stringArray = new String[len];

        for(short i = 0; i < len; i++)
        {
            short length = (short) (this.readShort() * 2);
            byte c[] = new byte[length];
            if(in.read(c, 0, length) == length)
                stringArray[i] = new String(c, "UTF-16BE");
            else
                throw new Exception("String : buffer is empty");
        }
        return stringArray;
    }
	
	public Boolean readBool() throws Exception
	{
		byte c[] = new byte[1];
		if(in.read(c, 0, 1) == 1)
			return c[0] == 1 ? true : false;
		else
			throw new Exception("Boolean : buffer is empty");
	}
	
	public ArrayList<McMetadata> readMetadata() throws Exception
	{
		ArrayList<McMetadata> metadataList = new ArrayList<McMetadata>();
		
		short x = this.readUByte();
		while(x != 127)
		{
			short index = (short) (x & 0x1F);
			short type = (short) (x >> 5);
			Object value = null;
			Object[] values = null;

			if(type == 0)
				value = this.readByte();
			if(type == 1)
				value = this.readShort();
			if(type == 2)
				value = this.readInt();
			if(type == 3)
				value = this.readFloat();
			if(type == 4)
				value = this.readString();
			if(type == 5)
			{
                values = new Object[5];
                short itemBlockId = this.readShort();
                values[0] = itemBlockId;
                if(itemBlockId != -1) {
                    values[1] = this.readByte();
                    values[2] = this.readShort();
                    short nbtDataLength = this.readShort();
                    values[3] = nbtDataLength;
                    if(nbtDataLength != -1) {
                        values[4] = this.readByteArray(nbtDataLength);
                    }
                }
			}
			if(type == 6)
			{
				values = new Object[3];
				values[0] = this.readInt();
				values[1] = this.readInt();
				values[2] = this.readInt();
			}
			
			McMetadata mData = null;
			
			if(value != null)
				mData = new McMetadata(index, type, value);
			else if(values != null)
				mData = new McMetadata(index, type, values);
			
			if(mData != null)
				metadataList.add(mData);
			
			x = this.readUByte();
		}
		
		return metadataList;
		
		//throw new Exception("readMetadata : can't handle this type");
	}
	
}
