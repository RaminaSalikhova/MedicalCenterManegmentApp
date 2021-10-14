package main;

import enums.HANDLER_TYPE;
import helpers.ObjectMessenger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.ClientRequest;
import models.ServerResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class App extends Application {

        private static Scene scene;

        @Override
        public void start(Stage stage) throws IOException {

            final Parent root = loadFXML("login");
//          scene = new Scene(loadFXML("login"));
          scene = new Scene(root);
          stage.setScene(scene);
            stage.setTitle("Calculator");

            stage.show();
        }

        static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
        }

        private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/"+fxml+".fxml"));

            return fxmlLoader.load();
        }

        public static void main(String[] args) {
            try {
                Socket s = new Socket("localhost", 36363);
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                DataInputStream din = new DataInputStream(s.getInputStream());
                Scanner scan = new Scanner(System.in);
                ObjectMessenger om = new ObjectMessenger(s);

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

            } catch (IOException  e) {
                e.printStackTrace();
            }
            launch(args);
        }
    }
