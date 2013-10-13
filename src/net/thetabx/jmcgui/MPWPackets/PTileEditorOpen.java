package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 13/10/13
 * Time: 03:40
 */
public class PTileEditorOpen extends MPWPacket {
    public static final short packetId = 0x85;
    // Last update 74

    // Server to client
    private byte tileEntityId;
    private int x;
    private int y;
    private int z;

    public PTileEditorOpen(TCPReader in) throws Exception {
        super(packetId);
        tileEntityId = in.readByte();
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
    }

    public byte getTileEntityId() {
        return tileEntityId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
