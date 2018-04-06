package training.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private BufferedReader in = null;
    private PrintWriter out = null;
    private ServerSocket servers = null;
    private Socket fromclient = null;
    private static final String SIGN = "[Server] ";

    public Server() {
        super();
        start();
    }

    public void run() {
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println(SIGN + "Couldn't listen to port 4444");
        }

        try {
            System.out.println(SIGN + "Waiting for a client...");
            fromclient = servers.accept();
            System.out.println(SIGN + "Client connected");
        } catch (IOException e) {
            System.out.println(SIGN + "Can't accept");
        }

        try {
            in = new BufferedReader(new
                    InputStreamReader(fromclient.getInputStream()));
            out = new PrintWriter(fromclient.getOutputStream(), true);
            String input, output;
            System.out.println(SIGN + "Wait for messages");
            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) break;
                //out.println("from Server ::: " + input);
                System.out.println(SIGN + input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
            in.close();
            fromclient.close();
            servers.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
