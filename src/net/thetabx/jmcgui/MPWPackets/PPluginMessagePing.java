package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McConstants;
import net.thetabx.jmcgui.TCPWriter;

public class PPluginMessagePing extends MPWPacket {

	private final static short packetId = 0xFA;
	private final PServerListPing pingPacket;
	private String hostname;
	private int port;

	// Client to server
	private final String pluginName = "MC|PingHost";
	/*
FE - packet identifier for a server list ping
01 - server list ping's payload (always 1)
FA - packet identifier for a plugin message
00 0B - length of following string, in characters, as a short (always 11)
00 4D 00 43 00 7C 00 50 00 69 00 6E 00 67 00 48 00 6F 00 73 00 74 - the string "MC|PingHost" encoded as a UTF-16BE string
XX XX - length of the rest of the data, as a short. Compute as 7 + 2*len(hostname)
XX - protocol version - currently 74 (decimal)
XX XX - length of following string, in characters, as a short
... - hostname the client is connecting to, encoded the same way as "MC|PingHost"
XX XX XX XX - port the client is connecting to, as an int.
	 */
	
	public PPluginMessagePing(String hostname, int port) {
		super(packetId);
		pingPacket = new PServerListPing();
		this.hostname = hostname;
		this.port = port;
	}

	public void send(TCPWriter out) throws Exception
	{
		pingPacket.send(out);
		out.writeUByte(packetId);
		out.writeString(pluginName);
		out.writeShort((short) (1 + 2 + 2 * hostname.length() + 4));
		out.writeUByte(McConstants.PROTOCOLVERSION);
		out.writeString(hostname);
		out.writeInt(port);
	}
}
