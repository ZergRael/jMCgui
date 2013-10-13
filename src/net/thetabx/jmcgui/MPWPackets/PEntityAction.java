package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

public class PEntityAction extends MPWPacket {
    private final static short packetId = 0x13;
    // Last update 74

    // Client to server
    private int eId;
    private byte actionId;
    private int jumpBoost;

    // Crouch = 1, Uncrouch = 2, LeaveBed = 3, StartSprinting = 4, StopSprinting = 5;

    public PEntityAction(int eId, byte actionId, int jumpBoost) {
        super(packetId);
        this.eId = eId;
        this.actionId = actionId;
        this.jumpBoost = jumpBoost;
    }

    public void send(TCPWriter out) throws Exception {
        out.writeUByte(packetId);
        out.writeInt(eId);
        out.writeByte(actionId);
        out.writeInt(jumpBoost);
    }
}
