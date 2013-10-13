package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.Utils.HTTPRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 10/10/13
 * Time: 23:28
 */
public class PEncryptionKeyRequest extends MPWPacket {
    public static final short packetId = 0xFD;
    // Last update 74

    // Server to client
    private String serverId;
    private short publicKeyLength;
    private byte[] publicKey;
    private short verifyTokenLength;
    private byte[] verifyToken;

    public PEncryptionKeyRequest(TCPReader in) throws Exception {
        super(packetId);
        serverId = in.readString();
        publicKeyLength = in.readShort();
        publicKey = in.readByteArray(publicKeyLength);
        verifyTokenLength = in.readShort();
        verifyToken = in.readByteArray(verifyTokenLength);
    }

    public void gDataMod(McGlobalData gData) {
        gData.setServerId(serverId);
        //gData.setRunning(false);

        if (gData.getServerId().equals("-")) {
            System.out.println("Offline mode");
            return;
        }

        // TODO Check identification
        // TODO Proper AES stream encryption
        if (gData.getSessionId() == null) {
            gData.stop(McGlobalData.StopReason.ERROR, "Not logged in");
            return;
        }

        System.out.println("Send login request to minecraft.net");
        HTTPRequest httpR = new HTTPRequest("http://session.minecraft.net/game/joinserver.jsp?user=" + gData.getNickname() + "&sessionId=" + gData.getSessionId() + "&serverId=" + gData.getServerId());
        httpR.send(false);
        String mcResponse = httpR.read();
        System.out.println(mcResponse);
        if (mcResponse.compareTo("OK") != 0) {
            gData.stop(McGlobalData.StopReason.ERROR, mcResponse);
        }
    }

    public MPWPacket getResponsePacket(McGlobalData gData) {
        return new PClientStatuses((byte) 0x00);
    }

    public String getServerId() {
        return serverId;
    }

    public short getPublicKeyLength() {
        return publicKeyLength;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public short getVerifyTokenLength() {
        return verifyTokenLength;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
