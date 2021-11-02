import app.handlers.ClientHandler;

import java.net.ServerSocket;
import java.net.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Server has stared");

            ServerSocket ss = new ServerSocket(36363);
            while (true) {
                Socket s = ss.accept();
                System.out.println("A new client is connected via " + s);
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                System.out.println("Assigning a new thread to the client");
                Thread t1 = new ClientHandler(s, dis, dout);
                t1.start();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}