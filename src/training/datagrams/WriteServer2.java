package training.datagrams;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WriteServer2 extends Thread {
    private static int i = 0;
    private static int serverPort = 998;
    private static int clientPort = 999;
    private static int portOut = 1000;
    private static int portIn = 1001;
    private static int buffer_size = 1024;
    private DatagramSocket ds;
    private byte buffer[] = new byte[buffer_size];

    private WriteServer2() {
        super();
        i++;
        //if (++i == 2) clientPort++;
        start();
    }

    public static WriteServer2 getInstance(/*int in, int out*/) {
        /*portIn = in;
        portOut = out;*/
        if (i <= 2) return new WriteServer2();
        else return null;
    }

    public synchronized void TheServer() throws Exception {
        int pos=0;
        while (true) {
            ds = new DatagramSocket(serverPort);
            int c = System.in.read();
            switch (c) {
                case -1:
                    System.out.println("Server Quits.");
                    ds.close();
                    return;
                case '\r':
                    break;
                case '\n':
                    ds.send(new DatagramPacket(buffer, pos,
                            InetAddress.getLocalHost(), portOut));
                    ds.close();
                    pos=0;
                    break;
                default:
                    buffer[pos++] = (byte) c;
            }
        }
    }

    public synchronized void TheClient() throws Exception {
        ds = new DatagramSocket(portIn);
        while(true) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            ds.receive(p);
            System.out.println(new String(p.getData(), 0, p.getLength()));
            ds.close();
        }
    }

    public void run() {
        try {
            if (i == 1) {
                TheServer();
            }
            if (i == 2) {
                TheClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
