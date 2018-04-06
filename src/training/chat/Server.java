package training.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public enum Server {       //extends Thread? //enum -> class
    SERVER;
    private HashMap<Login, Password> clientList;
    private ServerSocket serverSocket;
    public static final int SERV_PORT = 1000;

    Server() {
        clientList = new HashMap<>();
        try {
            serverSocket = new ServerSocket(SERV_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public void waitClients() {}
    public boolean addClient(String login, String password) {
        if (clientList.containsKey(login)) {
            System.out.println("Login " + login + " already exists.");
            return false;
        }
        if (clientList.containsValue(password)) {
            System.out.println("This password already exists.");
            return false;
        }
        clientList.put(new Login(login), new Password(password));
        return true;
    }

    public ArrayList<Login> getClients() {
        ArrayList<Login> clients = new ArrayList<>();
        for (Login l: clientList.keySet()) {
            clients.add(l);
        }
        return clients;
    }
}
