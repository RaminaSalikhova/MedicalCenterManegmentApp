package app.connection;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    private Socket s;
    private DataOutputStream dout;
    private DataInputStream din;
    private static ClientConnection instance;

    private ClientConnection() {
    }

    public ClientConnection(Socket s, DataOutputStream dout, DataInputStream din) {
        this.s = s;
        this.dout = dout;
        this.din = din;
    }

    public static ClientConnection createInstance() {
        if (instance == null) {
            try {
                Socket s = new Socket("localhost", 36363);
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                DataInputStream din = new DataInputStream(s.getInputStream());
                instance = new ClientConnection(s, dout, din);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static ClientConnection getInstance() {
        return instance==null ? instance : createInstance();
    }

    public void sendObject(Object object) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(object);
        oos.flush();
    }
    public Object receiveObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        Object object = ois.readObject();
        return object;
    }

}
