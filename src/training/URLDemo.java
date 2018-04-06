package training;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLDemo {
    public static void start() {
        URL url = null;
        HttpURLConnection urlc;
        String addr = "https://accounts.google.com/signin/";
        try {
            url = new URL(addr);
            urlc = (HttpURLConnection) url.openConnection();
            System.out.println(urlc.getResponseMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (url == null) return;
        /*System.out.println("Protocol: " + url.getProtocol());
        System.out.println("Port: " + url.getPort());
        System.out.println("Host: " + url.getHost());
        System.out.println("File: " + url.getFile());
        System.out.println("Full form: " + url.toExternalForm());*/
    }
}
