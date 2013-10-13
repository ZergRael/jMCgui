package net.thetabx.jmcgui;

import net.thetabx.jmcgui.MPWObjects.Metadata;
import net.thetabx.jmcgui.MPWObjects.SlotData;

import java.io.BufferedInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class TCPReader {

    private BufferedInputStream in = null;

    public TCPReader(BufferedInputStream in) {
        if (in != null)
            this.in = in;
    }

    public byte readByte() throws Exception {
        byte c[] = new byte[1];
        while (in.read(c, 0, 1) != 1) ;
        return c[0];
    }

    public byte[] readByteArray(int len) throws Exception {
        byte c[] = new byte[len];
        int read = 0;
        while (read != len)
            read += in.read(c, read, len - read);
        return c;
    }

    public short readUByte() throws Exception {
        byte c[] = new byte[1];
        while (in.read(c, 0, 1) != 1) ;
        return (short) (c[0] & 0xFF);
    }

    public short readShort() throws Exception {
        byte c[] = new byte[2];
        byte read = 0;
        while (read != 2)
            read += in.read(c, read, 2 - read);
        return ByteBuffer.wrap(c).getShort();
    }

    public int readUShort() throws Exception {
        byte c[] = new byte[2];
        byte read = 0;
        while (read != 2)
            read += in.read(c, read, 2 - read);
        return ByteBuffer.wrap(c).getShort() & 0xFFFF;
    }

    public int readInt() throws Exception {
        byte c[] = new byte[4];
        byte read = 0;
        while (read != 4)
            read += in.read(c, read, 4 - read);
        return ByteBuffer.wrap(c).getInt();
    }

    public long readLong() throws Exception {
        byte c[] = new byte[8];
        byte read = 0;
        while (read != 8)
            read += in.read(c, read, 8 - read);
        return ByteBuffer.wrap(c).getLong();
    }

    public float readFloat() throws Exception {
        byte c[] = new byte[4];
        byte read = 0;
        while (read != 4)
            read += in.read(c, read, 4 - read);
        return ByteBuffer.wrap(c).getFloat();
    }

    public double readDouble() throws Exception {
        byte c[] = new byte[8];
        byte read = 0;
        while (read != 8)
            read += in.read(c, read, 8 - read);
        return ByteBuffer.wrap(c).getDouble();
    }

    public String readString() throws Exception {
        short length = (short) (this.readShort() * 2);

        byte c[] = new byte[length];
        short read = 0;
        while (read != length)
            read += in.read(c, read, length - read);
        return new String(c, "UTF-16BE");
    }

    public String[] readStringArray(short len) throws Exception {
        if (len == 0)
            return new String[0];
        String[] stringArray = new String[len];

        for (short i = 0; i < len; i++) {
            short length = (short) (this.readShort() * 2);
            short read = 0;
            byte c[] = new byte[length];
            while (read != length)
                read += in.read(c, read, length - read);
            stringArray[i] = new String(c, "UTF-16BE");
        }
        return stringArray;
    }

    public Boolean readBool() throws Exception {
        byte c[] = new byte[1];
        while (in.read(c, 0, 1) != 1) ;
        return c[0] == 1;
    }

    public SlotData readSlotData() throws Exception {
        short itemBlockId = this.readShort();
        if (itemBlockId != -1) {
            byte itemCount = this.readByte();
            short itemDamage = this.readShort();
            short nbtDataLength = this.readShort();
            if (nbtDataLength != -1) {
                byte[] nbtData = this.readByteArray(nbtDataLength);
                return new SlotData(itemBlockId, itemCount, itemDamage, nbtDataLength, nbtData);
            } else
                return new SlotData(itemBlockId, itemCount, itemDamage);
        } else
            return new SlotData();
    }

    public ArrayList<Metadata> readMetadata() throws Exception {
        ArrayList<Metadata> metadataList = new ArrayList<Metadata>();

        short x = this.readUByte();
        while (x != 127) {
            short index = (short) (x & 0x1F);
            short type = (short) (x >> 5);
            Object value = null;
            Object[] values = null;

            if (type == 0)
                value = this.readByte();
            if (type == 1)
                value = this.readShort();
            if (type == 2)
                value = this.readInt();
            if (type == 3)
                value = this.readFloat();
            if (type == 4)
                value = this.readString();
            if (type == 5) {
                values = new Object[5];
                short itemBlockId = this.readShort();
                values[0] = itemBlockId;
                if (itemBlockId != -1) {
                    values[1] = this.readByte();
                    values[2] = this.readShort();
                    short nbtDataLength = this.readShort();
                    values[3] = nbtDataLength;
                    if (nbtDataLength != -1) {
                        values[4] = this.readByteArray(nbtDataLength);
                    }
                }
            }
            if (type == 6) {
                values = new Object[3];
                values[0] = this.readInt();
                values[1] = this.readInt();
                values[2] = this.readInt();
            }

            Metadata mData = null;

            if (value != null)
                mData = new Metadata(index, type, value);
            else if (values != null)
                mData = new Metadata(index, type, values);

            if (mData != null)
                metadataList.add(mData);

            x = this.readUByte();
        }

        return metadataList;

        //throw new Exception("readMetadata : can't handle this type");
    }

}
