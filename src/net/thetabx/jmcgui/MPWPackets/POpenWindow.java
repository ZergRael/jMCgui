package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

public class POpenWindow extends MPWPacket {
    private final static short packetId = 0x64;
    // Last update 74

    // Server to client
    private byte windowId;
    private byte inventoryType;
    private String windowTitle;
    private byte numberOfSlots;
    private boolean userProvidedWindowTitle;
    private int entityId;

    public POpenWindow(TCPReader in) throws Exception {
        super(packetId);
        windowId = in.readByte();
        inventoryType = in.readByte();
        windowTitle = in.readString();
        numberOfSlots = in.readByte();
        userProvidedWindowTitle = in.readBool();
        if (inventoryType == 11)
            entityId = in.readInt();
    }

    public byte getWindowId() {
        return windowId;
    }

    public byte getInventoryType() {
        return inventoryType;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public byte getNumberOfSlots() {
        return numberOfSlots;
    }

    public boolean isUserProvidedWindowTitle() {
        return userProvidedWindowTitle;
    }

    public int getEntityId() {
        return entityId;
    }
}
