package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPWriter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 13/10/13
 * Time: 03:34
 */
public class PClientSettings extends MPWPacket {
    public static final short packetId = 0xCC;
    // Last update 74

    // Client to server
    private String locale;
    private byte viewDistance;
    private byte chatFlags;
    private byte difficulty;
    private boolean showCape;

    public PClientSettings(String locale, byte viewDistance, byte chatFlags, byte difficulty, boolean showCape) {
        super(packetId);
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatFlags = chatFlags;
        this.difficulty = difficulty;
        this.showCape = showCape;
    }

    public void send(TCPWriter out) throws IOException {
        out.writeUByte(packetId);
        out.writeString(locale);
        out.writeByte(viewDistance);
        out.writeByte(chatFlags);
        out.writeByte(difficulty);
        out.writeBool(showCape);
    }
}
