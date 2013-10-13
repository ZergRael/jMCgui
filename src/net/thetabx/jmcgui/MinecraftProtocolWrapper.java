package net.thetabx.jmcgui;

import net.thetabx.jmcgui.Utils.CustEvent;
import net.thetabx.jmcgui.Utils.CustEventListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MinecraftProtocolWrapper {

    private McThread tManager = null;
    private McGlobalData globalData = new McGlobalData();

    private Socket client = null;

    private BufferedInputStream in = null;
    private BufferedOutputStream out = null;

    public MinecraftProtocolWrapper(String address, int port) {
        globalData.setAddress(address);
        globalData.setPort(port);
    }

    public Boolean connect() {
        // TCP Connect

        try {
            client = new Socket(globalData.getAddress(), globalData.getPort());
        } catch (IOException e) {

            return false;
        }

        // Get buffered reader/writer
        try {
            in = new BufferedInputStream(client.getInputStream());
            out = new BufferedOutputStream(client.getOutputStream());
        } catch (IOException e) {

            return false;
        }

        tManager = new McThread(in, out, globalData);

        tManager.addCustEventListener(new CustEventListener() { //Fired when tManager has done closing work
            public void custEventOccurred(CustEvent evt) {
                System.out.println("custEventOccurred : " + evt.getTitle());
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        tManager.start();
        return true;
    }

    public void disconnect() {
        if (tManager.isRunning())
            tManager.stopNow(false);

		/*try {
            client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    }

    public void setNick(String nick) {
        globalData.setNickname(nick);
    }

    public void setSessionId(String sessionId) {
        globalData.setSessionId(sessionId);
    }

    public McGlobalData getGData() {
        return globalData;
    }
}
