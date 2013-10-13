package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class PItemData extends MPWPacket {
    private final static short packetId = 0x83;
    // Last update 74

    // Server to client
    private short itemType;
    private short itemId;
    private short dataLength;
    private byte[] data;

    public PItemData(TCPReader in) throws Exception {
        super(packetId);
        itemType = in.readShort();
        itemId = in.readShort();
        dataLength = in.readShort();
        data = in.readByteArray(dataLength);
    }

    public short getItemType() {
        return itemType;
    }

    public short getItemId() {
        return itemId;
    }

    public short getDataLength() {
        return dataLength;
    }

    public byte[] getData() {
        return data;
    }
}
