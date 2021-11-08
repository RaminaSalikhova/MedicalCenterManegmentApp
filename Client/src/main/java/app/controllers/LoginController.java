package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.LoginDto;
import app.models.DataTransferModels.RegistrationDto;
import app.models.holder.UserHolder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private Label lblErrors;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnMain, btnSignUp, btnSignIn;

    private String role;

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == btnMain) {  ////button to go back to main page
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/main.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
                stage.setResizable(false);
//                stage.setFullScreen(true);
//                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == btnSignUp) {   ///button to go to signup page on being new user
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/register.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
                stage.setResizable(false);
//                stage.setFullScreen(true);
//                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == btnSignIn) {   ////button to check signin and go to patient's dashboard
            if (logIn().equals("Success")) {
                try {
                    UserHolder userHolder=UserHolder.getInstance();
                    userHolder.setUser(getUserAtm());

                    Stage stage = new Stage();
                    Pane root;
                    switch (role){
                        case "ADMIN":{
                            root = FXMLLoader.load(getClass().getResource("/adminHome.fxml"));
                            stage.setScene(new Scene(root));
                            break;
                        }
                        case "PATIENT":{
                            root = FXMLLoader.load(getClass().getResource("/patientHome.fxml"));
                            stage.setScene(new Scene(root));
                            break;
                        }
                        case "DOCTOR":{
                            root = FXMLLoader.load(getClass().getResource("/doctorHome.fxml"));
                            stage.setScene(new Scene(root));
                            break;
                        }
                    }
                    stage.show();
                    stage.setResizable(false);
//                stage.setFullScreen(true);
//                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

                    ((Node) (ae.getSource())).getScene().getWindow().hide();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private String logIn() {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(txtUsername.getText());

        if (txtUsername.getText().isEmpty()
                && txtPassword.getText().isEmpty()) {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните все поля");
            return "Fail";
        } else if(!matcher.matches())
        {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните поле почты в соответсвующем формате");
            return "Fail";
        }
        else {
            LoginDto loginDto = new LoginDto();
            loginDto.setLogin(txtUsername.getText());
            loginDto.setPassword(txtPassword.getText());

            ClientRequest<LoginDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.login);
            request.setData(loginDto);

            try {
                ClientConnection.getInstance().sendObject(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerResponse<UserAtm> response = null;
            try {
                response = (ServerResponse) ClientConnection.getInstance().receiveObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
            if (response.getStatus() == true) {
//                lblErrors.setText(response.getData().toString());
                System.out.println(response.getData());
                UserAtm userAtm=response.getData();
                role= userAtm.getRole();
                return "Success";
            } else {
                lblErrors.setVisible(true);
                lblErrors.setText(response.getMessage());
                System.out.println(response.getMessage());
                return "Fail";
            }
        }
    }

    private UserAtm getUserAtm() {
            LoginDto loginDto = new LoginDto();
            loginDto.setLogin(txtUsername.getText());
            loginDto.setPassword(txtPassword.getText());

            ClientRequest<LoginDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.getUserData);
            request.setData(loginDto);

            try {
                ClientConnection.getInstance().sendObject(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerResponse response = null;
            try {
                response = (ServerResponse) ClientConnection.getInstance().receiveObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
            if (response.getStatus() == true) {
//                lblErrors.setText(response.getData().toString());
                System.out.println(response.getData());
                return (UserAtm) response.getData();
            } else {
                lblErrors.setVisible(true);
                lblErrors.setText(response.getMessage());
                System.out.println(response.getMessage());
                return  (UserAtm) response.getData();
            }

    }
}