package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

/**
 * Created with IntelliJ IDEA.
 * User: zerg
 * Date: 10/12/13
 * Time: 10:31 PM
 */
public class PSteerVehicle extends MPWPacket {
    public static final short packetId = 0x1B;
    // Last update 74

    // Client to server
    private float sideways;
    private float forward;
    private boolean jump;
    private boolean unmount;

    public PSteerVehicle(float sideways, float forward, boolean jump, boolean unmount)
    {
        super(packetId);
        this.sideways = sideways;
        this.forward = forward;
        this.jump = jump;
        this.unmount = unmount;
    }

    public void send(TCPWriter out) throws Exception
    {
        out.writeUByte(packetId);
        out.writeFloat(sideways);
        out.writeFloat(forward);
        out.writeBool(jump);
        out.writeBool(unmount);
    }
}
