package app.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainController {
    @FXML
    private JFXButton btnSignUp, btnSignIn = new JFXButton();


    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        try {
            Stage stage;
            Pane root;
            if (ae.getSource() == btnSignIn) {
                stage = new Stage();
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);
                stage.setFullScreen(false);
//                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } else if (ae.getSource() == btnSignUp) {
                stage = new Stage();
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/register.fxml")));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);
                stage.setFullScreen(false);
//                stage.setFullScreen(true);
//                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } else {
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
