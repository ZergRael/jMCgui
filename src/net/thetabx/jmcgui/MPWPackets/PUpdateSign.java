package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

public class PUpdateSign extends MPWPacket {
    private final static short packetId = 0x82;
    // Last update 74

    // 2 Way
    private int x;
    private short y;
    private int z;
    private String text1;
    private String text2;
    private String text3;
    private String text4;

    public PUpdateSign(TCPReader in) throws Exception {
        super(packetId);
        x = in.readInt();
        y = in.readShort();
        z = in.readInt();
        text1 = in.readString();
        text2 = in.readString();
        text3 = in.readString();
        text4 = in.readString();
    }

    public PUpdateSign(int x, short y, int z, String text1, String text2, String text3, String text4) {
        super(packetId);
        this.x = x;
        this.y = y;
        this.z = z;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeInt(x);
        out.writeShort(y);
        out.writeInt(z);
        out.writeString(text1);
        out.writeString(text2);
        out.writeString(text3);
        out.writeString(text4);
    }

    public int getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }
}
