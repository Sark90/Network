package training.chat;

import java.util.ArrayList;

public class Client {   //extends Thread?
    private Login login;
    private Password password;
    private ArrayList<Login> contactList;

    public Client(Login login, Password password) {
        this.login = login;
        this.password = password;   //TODO: encode password
        contactList = new ArrayList<>();    //TODO: get from server
    }

    public void connect() {}
    public void registration() {}

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
