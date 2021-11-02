package app;

import app.connection.ClientConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        final Parent root = loadFXML("main");
//          scene = new Scene(loadFXML("login"));
        scene = new Scene(root, 1024, 640);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setTitle("Hospital system");

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));

        return fxmlLoader.load();
    }


    public static void main(String[] args) {
//        try(Socket s = new Socket("localhost", 36363);
//            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
//            DataInputStream din = new DataInputStream(s.getInputStream()))
//        {
//            Socket s = new Socket("localhost", 36363);
//            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
//            DataInputStream din = new DataInputStream(s.getInputStream());
//            Scanner scan = new Scanner(System.in);
//            ObjectMessenger om = new ObjectMessenger(s);

//                ClientRequest<String> request = new ClientRequest<>();
//                request.setType(HANDLER_TYPE.register);
//                request.setData("registrationDto");
//                om.sendObject(request);
//
//                ServerResponse response = (ServerResponse) om.receiveObject();
//                if (response.getStatus() == true) {
//                    System.out.println(response.getData());
//                } else {
//                    System.out.println(response.getMessage());
//                }


//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ClientConnection.createInstance();
//        ClientRequest<String> request = new ClientRequest<>();
//        request.setType(HANDLER_TYPE.register);
//        request.setData("registrationDto");

//        try {
//            ClientConnection.getInstance().sendObject(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ServerResponse response = null;
//        try {
//            response = (ServerResponse)  ClientConnection.getInstance().receiveObject();
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }
//        if (response.getStatus() == true) {
//            System.out.println(response.getData());
//        } else {
//            System.out.println(response.getMessage());
//        }

        launch(args);
    }
}
