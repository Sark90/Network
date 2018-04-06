package datagrams;

public class Datagrams extends Thread {
    private static int countCl = 0, countServ = 0;
    private int serverPort;
    private int clientPort;
    public static final int BUFFER_SIZE = 1024;
    private Modes mode;

    private Datagrams(Modes mode, int serverPort, int clientPort) {
        super();
        this.serverPort = serverPort;
        this.clientPort = clientPort;
        this.mode = mode;
        start();
    }

    public static Datagrams getInstance(Modes mode, int serverPort, int clientPort) {
        switch (mode) {
            case Client:
                if (++countCl > 1) {
                    System.out.println("No more than 1 client allowed!");
                    return null;
                }
                break;
            case Server:
                if (++countServ > 1) {
                    System.out.println("No more than 1 server allowed!");
                    return null;
                }
        }
        return new Datagrams(mode, serverPort, clientPort);
    }

    public void run() {
        switch (mode) {
            case Client:
                Receiver.receive(clientPort);
                break;
            case Server:
                Sender.send(serverPort, clientPort);
                break;
            default:
                System.out.println("Incorrect mode");
        }
    }
}
