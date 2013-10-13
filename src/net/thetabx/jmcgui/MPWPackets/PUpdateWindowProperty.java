package net.thetabx.jmcgui.MPWPackets;


import net.thetabx.jmcgui.TCPReader;

public class PUpdateWindowProperty extends MPWPacket {
    private final static short packetId = 0x69;
    // Last update 74

    // Server to client
    private byte windowId;
    private short property;
    private short value;

    public PUpdateWindowProperty(TCPReader in) throws Exception {
        super(packetId);
        windowId = in.readByte();
        property = in.readShort();
        value = in.readShort();
    }

    public byte getWindowId() {
        return windowId;
    }

    public short getProperty() {
        return property;
    }

    public short getValue() {
        return value;
    }
}
