package net.thetabx.jmcgui;

import net.thetabx.jmcgui.MPWPackets.MPWPacket;
import net.thetabx.jmcgui.MPWPackets.Packet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MPWReceiveThread extends Thread {

    private Boolean run = false;
    private final Object lock = new Object();

    private List<MPWPacket> toReceive = new LinkedList<MPWPacket>();

    //private BufferedReader in;
    private BufferedInputStream in;
    private TCPReader reader;

    //public MPWReceiveThread(BufferedReader in)
    public MPWReceiveThread(BufferedInputStream in) {
        this.setName("ReceiveThread");
        if (in == null)
            return;

        this.in = in;

        reader = new TCPReader(in);
    }

    public void run() {
        run = true;
        Short lastPacketId = -1;
        while (run) {
            try {
                lastPacketId = reader.readUByte();
                Class<MPWPacket> c = Packet.fromCode(lastPacketId);
                MPWPacket p = c.getConstructor(new Class[]{TCPReader.class}).newInstance(reader);

                // Working one // But not debug explicit
                //MPWPacket p = Packet.fromCode(reader.readUByte()).getConstructor(new Class[] {TCPReader.class}).newInstance(reader);
                synchronized (lock) {
                    toReceive.add(p);
                }
            } catch (Exception e1) {
                this.stopNow();
                System.out.print(String.format("Failed on %02X", lastPacketId));
                e1.printStackTrace();
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
        System.out.println("Stopping rThread");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean isRunning() {
        return run;
    }

    public MPWPacket getFirst() {
        if (!toReceive.isEmpty()) {
            MPWPacket p;
            synchronized (lock) {
                p = toReceive.remove(0);
            }
            return p;
        }
        return null;
    }

    public Boolean isEmpty() {
        return toReceive.isEmpty();
    }

    public int listSize() {
        return toReceive.size();
    }

    public void clear() {
        synchronized (lock) {
            toReceive.clear();
        }
    }
}
