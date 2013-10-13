package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McConstants;
import net.thetabx.jmcgui.TCPWriter;

public class PPluginMessagePing extends MPWPacket {
    private final static short packetId = 0xFA;
    // Last update 74

    private final PServerListPing pingPacket;
    private String hostname;
    private int port;

    // Client to server
    private final String pluginName = "MC|PingHost";

    public PPluginMessagePing(String hostname, int port) {
        super(packetId);
        pingPacket = new PServerListPing();
        this.hostname = hostname;
        this.port = port;
    }

    public void send(TCPWriter out) throws Exception {
        pingPacket.send(out);
        out.writeUByte(packetId);
        out.writeString(pluginName);
        out.writeShort((short) (1 + 2 + 2 * hostname.length() + 4));
        out.writeUByte(McConstants.PROTOCOLVERSION);
        out.writeString(hostname);
        out.writeInt(port);
    }
}
