package net.thetabx.jmcgui.MPWPackets;

import net.thetabx.jmcgui.TCPReader;
import net.thetabx.jmcgui.TCPWriter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Zerg
 * Date: 13/10/13
 * Time: 03:28
 */
public class PEncryptionKeyResponse extends MPWPacket {
    public static final short packetId = 0xFC;
    // Last update 74

    // Two way
    //private short sharedSecretLength;
    private byte[] sharedSecret;
    //private short verifyTokenLength;
    private byte[] verifyTokenResponse;

    //public PEncryptionKeyResponse(short sharedSecretLength, byte[] sharedSecret, short verifyTokenLength, byte[] verifyTokenResponse)
    public PEncryptionKeyResponse(byte[] sharedSecret, byte[] verifyTokenResponse) {
        super(packetId);
        this.sharedSecret = sharedSecret;
        this.verifyTokenResponse = verifyTokenResponse;
    }

    public PEncryptionKeyResponse(TCPReader in) throws Exception {
        super(packetId);
        sharedSecret = in.readByteArray(in.readShort());
        verifyTokenResponse = in.readByteArray(in.readShort());
    }

    public void send(TCPWriter out) throws IOException {
        out.writeUByte(packetId);
        out.writeShort((short) sharedSecret.length);
        out.writeByteArray(sharedSecret);
        out.writeShort((short) verifyTokenResponse.length);
        out.writeByteArray(verifyTokenResponse);
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyTokenResponse() {
        return verifyTokenResponse;
    }
}
