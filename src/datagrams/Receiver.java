package datagrams;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static datagrams.Datagrams.BUFFER_SIZE;

public abstract class Receiver {
    private static DatagramSocket ds;
    private static byte buffer[] = new byte[BUFFER_SIZE];

    public static void receive(int clientPort) {
        try {
            ds = new DatagramSocket(clientPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while(true) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            try {
                ds.receive(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("\t\t[receive to port " + clientPort + "]");
            System.out.println("\t\t\t" + new String(p.getData(), 0, p.getLength()));
        }
    }
}
