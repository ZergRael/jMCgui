package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McConstants;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;
import net.thetabx.jmcgui.Utils.HTTPRequest;

public class PHandshake extends MPWPacket{
	private final static short packetId = 0x02;
    // Last update 74

	// Client to server
	private String username;
    private String host;
    private int port;
	
	public PHandshake(String username, String host, int port)
	{
		super(packetId);
		this.username = username;
        this.host = host;
        this.port = port;
	}
	
	public void send(TCPWriter out) throws Exception
	{
		out.writeUByte(packetId);
        out.writeUByte(McConstants.PROTOCOLVERSION);
		out.writeString(username);
        out.writeString(host);
        out.writeInt(port);
	}
}