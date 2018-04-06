package datagrams;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import static datagrams.Datagrams.BUFFER_SIZE;

public abstract class Sender {
    private static DatagramSocket ds;
    private static byte buffer[] = new byte[BUFFER_SIZE];

    public static void send(int serverPort, int clientPort) {
        try {
            ds = new DatagramSocket(serverPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        int pos = 0;
        /*try {
            System.out.print("[send to " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(":" + clientPort + "]");*/
        while (true) {
            int c = 0;
            try {
                c = System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (c) {
                case -1:
                    System.out.println("Exit.");
                    ds.close();
                    return;
                case '\r':
                    break;
                case '\n':
                    try {
                        ds.send(new DatagramPacket(buffer, pos,
                                InetAddress.getLocalHost(), clientPort));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pos = 0;
                    break;
                default:
                    buffer[pos++] = (byte) c;
            }
        }
    }
}
