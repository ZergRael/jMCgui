package net.thetabx.jmcgui;

import net.thetabx.jmcgui.MPWPackets.PDisconnectKick;
import net.thetabx.jmcgui.MPWPackets.PPluginMessagePing;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MPWPinger {

    private Socket client;
    //private BufferedReader in = null;
    //private OutputStreamWriter out = null;
    private BufferedInputStream in = null;
    private BufferedOutputStream out = null;

    private long ping;
    private String address;
    private int port;
    private String color;
    private String serverProtocolVersion;
    private String serverVersion;
    private String description;
    private String nUsers;
    private String nUsersMax;

    public MPWPinger(String address, int port) {
        try {
            client = new Socket(address, port);
            client.setSoTimeout(1000);
            //in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //out = new OutputStreamWriter(client.getOutputStream());
            in = new BufferedInputStream(client.getInputStream());
            out = new BufferedOutputStream(client.getOutputStream());

            this.address = address;
            this.port = port;
        } catch (Exception ignored) { }
    }

    public boolean ping() {
        if (in == null || out == null)
            return false;

        TCPWriter writer = new TCPWriter(out);
        TCPReader reader = new TCPReader(in);

        PDisconnectKick pResult = null;
        PPluginMessagePing p = new PPluginMessagePing(address, port);
        long pingStart = System.currentTimeMillis();
        try {
            p.send(writer);
            out.flush();

            if (reader.readUByte() != 0xFF)
                return false;
            pResult = new PDisconnectKick(reader);
        } catch (SocketTimeoutException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ping = System.currentTimeMillis() - pingStart;

        if (pResult == null)
            return false;

        String[] pResultFields = pResult.getReason().split("\0");
        if (pResultFields.length != 6)
            return false;

        try {
            out.close();
            in.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.color = pResultFields[0];
        this.serverProtocolVersion = pResultFields[1];
        this.serverVersion = pResultFields[2];
        this.description = pResultFields[3];
        this.nUsers = pResultFields[4];
        this.nUsersMax = pResultFields[5];

        return true;
    }

    public long getPing() {
        return this.ping;
    }

    public String getDescription() {
        return this.description;
    }

    public String getNUsers() {
        return this.nUsers;
    }

    public String getNUsersMax() {
        return this.nUsersMax;
    }

    public String getColor() {
        return color;
    }

    public String getServerProtocolVersion() {
        return serverProtocolVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }


}
