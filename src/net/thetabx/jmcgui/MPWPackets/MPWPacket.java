package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPWriter;

abstract public class MPWPacket {

    private short packetId;

    public MPWPacket(short packetId) {
        this.packetId = packetId;
    }

    public void send(TCPWriter out) throws Exception { }

    public MPWPacket getResponsePacket(McGlobalData gData) {
        return null;
    }

    public void gDataMod(McGlobalData gData) { }

    public short getPacketId() {
        return this.packetId;
    }

	/*public Object[] getFields() {
        return null;
	}*/
}
