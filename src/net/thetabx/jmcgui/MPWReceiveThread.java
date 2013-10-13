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
        while (run) {
            try {

                //String packetName = Packet.getName(reader.readUByte());
                // TODO Find a workaround for the SuppressWarnings
                //@SuppressWarnings("unchecked")
                //Class<MPWPacket> c = (Class<MPWPacket>) Class.forName("thetabx.jMCgui.MPWPackets.P" + packetName);
                //MPWPacket p = (MPWPacket)c.getConstructor(new Class[] {Class.forName("thetabx.jMCgui.TCPReader")}).newInstance(reader);

                //Class<MPWPacket> c = Packet.fromCode(reader.readUByte());
                //MPWPacket p = (MPWPacket)c.getConstructor(new Class[] {TCPReader.class}).newInstance(reader);


                short packetId = reader.readUByte();
                System.out.print(String.format("%02X - ", packetId));
                Class<MPWPacket> c = Packet.fromCode(packetId);
                System.out.println(c.getCanonicalName());

                MPWPacket p = c.getConstructor(new Class[]{TCPReader.class}).newInstance(reader);

                // Working one // But not debug explicit
                //MPWPacket p = Packet.fromCode(reader.readUByte()).getConstructor(new Class[] {TCPReader.class}).newInstance(reader);
                synchronized (lock) {
                    toReceive.add(p);
                }
                p = null;
            } catch (Exception e1) {
                this.stopNow();
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
