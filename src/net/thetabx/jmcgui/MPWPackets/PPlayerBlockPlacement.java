package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.MPWObjects.SlotData;
import net.thetabx.jmcgui.TCPWriter;

public class PPlayerBlockPlacement extends MPWPacket {
    private final static short packetId = 0x0F;
    // Last update 74

    // Client to server
    private int x;
    private short y;
    private int z;
    private byte direction;
    private SlotData slotData;
    private byte cursorPosX;
    private byte cursorPosY;
    private byte cursorPosZ;

    public PPlayerBlockPlacement(int x, short y, int z, byte direction, SlotData slotData, byte cursorPosX, byte cursorPosY, byte cursorPosZ) {
        super(packetId);
        this.x = x;
        this.y = y;
        this.z = z;
        this.direction = direction;
        this.slotData = slotData;
        this.cursorPosX = cursorPosX;
        this.cursorPosY = cursorPosY;
        this.cursorPosZ = cursorPosZ;
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeInt(x);
        out.writeUByte(y);
        out.writeInt(z);
        out.writeByte(direction);
        out.writeSlotData(slotData);
        out.writeByte(cursorPosX);
        out.writeByte(cursorPosY);
        out.writeByte(cursorPosZ);
    }
}
