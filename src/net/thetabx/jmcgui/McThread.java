package net.thetabx.jmcgui;

import net.thetabx.jmcgui.MPWEntities.ESelf;
import net.thetabx.jmcgui.MPWPackets.*;
import net.thetabx.jmcgui.Utils.CustEvent;
import net.thetabx.jmcgui.Utils.CustEventListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class McThread extends Thread {

    private int tick = 0;

    private MPWSendThread tSend;
    private MPWReceiveThread tReceive;
    private McGlobalData gData;

    public McThread(BufferedInputStream in, BufferedOutputStream out, McGlobalData gData) {
        this.setName("McThread");
        tSend = new MPWSendThread(out);
        tReceive = new MPWReceiveThread(in);
        this.gData = gData;
    }

    public void run() {
        if (gData.isRunning())
            gData.stop(McGlobalData.StopReason.ERROR, "Already running, crash now");
        gData.setRunning(true);
        tSend.add(new PHandshake(gData.getNickname(), gData.getAddress(), gData.getPort()));
        tSend.start();
        tReceive.start();
        while (gData.isRunning()) {
            MPWPacket p = null;
            if (tReceive.isRunning())
                p = tReceive.getFirst();
            MPWPacket pResp = null;
            if (p != null) {
                p.gDataMod(gData);
                if (!gData.isRunning()) {
                    this.stopNow(true);
                    continue;
                }
                pResp = p.getResponsePacket(gData);
            }
            p = null;

            if (pResp != null && tSend.isRunning())
                tSend.add(pResp);

            pResp = null;

            String messageToSend = gData.getToSendChatMessage();
            if (messageToSend != null) {
                int length = messageToSend.length();
                int nToSend = (length / 100);
                for (int i = 0; i < nToSend; i++)
                    tSend.add(new PChatMessage(messageToSend.substring(i * 100, ((i + 1) * 100))));
                tSend.add(new PChatMessage(messageToSend.substring(nToSend * 100, length)));
            }

            // Send position every 50ms
            if ((float) (tick * McConstants.MAINTHREADDELAY) % 50.0 < 1.0) {
                ESelf self = (ESelf) gData.getEntity(gData.getPlayerEntityId());
                if (self != null) {
                    tSend.add(new PPlayerPositionLook(self.getX(), self.getY(), self.getY() + McConstants.PLAYERSTANCE, self.getZ(), self.getYaw(), self.getPitch(), self.getOnGround()));
                }
            }

            try {
                Thread.sleep(McConstants.MAINTHREADDELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tick++;
            if (tick >= 1000)
                tick = 0;
        }
    }

    public void stopNow(boolean kicked) {
        System.out.println("tManager shutdown");
        tReceive.clear();
        tSend.clear();

        if (!kicked) {
            System.out.println("Stopping threads");
            gData.stop(McGlobalData.StopReason.DISCONNECT, "Bye");

            tSend.addFirst(new PDisconnectKick("Quitting"));
            int tick = 0;
            while (!tSend.isEmpty() && tick <= 20) // Allowing 1sec delay before closing send socket
            {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tick++;
            }
            System.out.println("Closed after " + tick + " ticks");

            tReceive.stopNow();
            tSend.stopNow();
        }

		/*try {
            Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

        fireCustEvent(new CustEvent(this, "McThread ended"));
    }

    public Boolean isRunning() {
        return gData.isRunning();
    }

    // Create the listener list
    protected javax.swing.event.EventListenerList listenerList =
            new javax.swing.event.EventListenerList();

    // This methods allows classes to register for MyEvents
    public void addCustEventListener(CustEventListener listener) {
        listenerList.add(CustEventListener.class, listener);
    }

    // This methods allows classes to unregister for MyEvents
    public void removeCustEventListener(CustEventListener listener) {
        listenerList.remove(CustEventListener.class, listener);
    }

    // This private class is used to fire MyEvents
    void fireCustEvent(CustEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == CustEventListener.class) {
                ((CustEventListener) listeners[i + 1]).custEventOccurred(evt);
            }
        }
    }
}
