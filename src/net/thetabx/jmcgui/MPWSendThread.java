package net.thetabx.jmcgui;

import net.thetabx.jmcgui.MPWPackets.MPWPacket;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;


public class MPWSendThread extends Thread {

    private Boolean run = false;

    private List<MPWPacket> toSend = new LinkedList<MPWPacket>();
    private final Object lock = new Object();

    //private OutputStreamWriter out;
    private BufferedOutputStream out;
    private TCPWriter writer;

    //public MPWSendThread(OutputStreamWriter out)
    public MPWSendThread(BufferedOutputStream out) {
        this.setName("SendThread");
        if (out == null)
            return;
        this.out = out;
        writer = new TCPWriter(out);
    }

    public void run() {
        run = true;
        while (run) {
            if (!toSend.isEmpty()) {
                MPWPacket p;
                synchronized (lock) {
                    p = toSend.remove(0);
                }
                try {
                    p.send(writer);
                    out.flush();
                } catch (SocketException e) {
                    stopNow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            try {
                Thread.sleep(McConstants.RECEIVESENDTHREADDELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopNow() {
        run = false;
        System.out.println("Stopping sThread");
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean isRunning() {
        return run;
    }

    public void add(MPWPacket packet) {
        synchronized (lock) {
            toSend.add(packet);
        }
    }

    public void addFirst(MPWPacket packet) {
        synchronized (lock) {
            toSend.add(0, packet);
        }
    }

    public Boolean isEmpty() {
        return toSend.isEmpty();
    }

    public void clear() {
        synchronized (lock) {
            toSend.clear();
        }
    }
}
