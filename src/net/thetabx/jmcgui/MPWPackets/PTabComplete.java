package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 13/10/13
 * Time: 03:37
 */
public class PTabComplete extends MPWPacket {
    public static final short packetId = 0xCB;
    // Last update 74

    // Two way
    private String text;

    public PTabComplete(String text) {
        super(packetId);
        this.text = text;
    }

    public PTabComplete(TCPReader in) throws Exception {
        super(packetId);
        text = in.readString();
    }

    public void send(TCPWriter out) throws IOException {
        out.writeUByte(packetId);
        out.writeString(text);
    }

    public String getText() {
        return text;
    }
}
