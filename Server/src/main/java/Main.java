import app.handlers.ClientHandler;
import app.utils.ConfigHelper;

import java.net.ServerSocket;
import java.net.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Server has stared");
            ConfigHelper configHelper=new ConfigHelper();
            String portConfig=null;

            try{
                portConfig=configHelper.getPropertyValue("port");
            }catch (IOException exception){
                exception.printStackTrace();
            }

            if(portConfig==null || portConfig.isEmpty()){
                System.out.println("Порт не получен");
                return;
            }

            int port=Integer.parseInt(portConfig);
            ServerSocket ss = new ServerSocket(port);
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