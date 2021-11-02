package app.helpers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObjectMessenger {
    protected final Socket socket;
    public ObjectMessenger(Socket socket) throws UnknownHostException, IOException {
        this.socket = socket;
    }
    public void sendObject(Object object) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(object);
        oos.flush();
    }
    public Object receiveObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Object object = ois.readObject();
        return object;
    }
}