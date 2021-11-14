package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.*;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import app.models.DataTransferModels.RegistrationDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrationController {
    @FXML
    private JFXTextField txtPhoneNumber, txtUsername, txtLastName, txtFirstName, txtPatronymic, txtPasscode;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnMain, btnCreate, btnSignIn, btnExit = new JFXButton();
    @FXML
    private DatePicker txtDob;
    @FXML
    private Label lblErrors;
    @FXML
    private JFXCheckBox checkBox;

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == btnMain) {    ///button to go back to app.main page
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/main.fxml"));
                stage.setScene(new Scene(root, 1024,640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == btnSignIn) {    ///button to go back to signin page
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));
                stage.setScene(new Scene(root, 1024,640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == btnCreate) {      ///button to create patient account
            createPressed();
        }
    }

    @FXML
    private void onCheckBoxAction() {
        if (checkBox.isSelected()) {
            txtPasscode.setEditable(true);
            txtPasscode.setDisable(false);
        }else{
            txtPasscode.setEditable(false);
            txtPasscode.setDisable(true);
        }
    }

    private void createPressed() {
        //check if all fields are filled
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(txtUsername.getText());

        String numberRegex =  "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern patterNumber = Pattern.compile(numberRegex);
        Matcher matcherNumber = patterNumber.matcher(txtPhoneNumber.getText());

        LocalDate value=txtDob.getValue();

        if (txtFirstName.getText().isEmpty()
                && txtLastName.getText().isEmpty()
                && txtPhoneNumber.getText().isEmpty()
                && txtUsername.getText().isEmpty()
                && txtPassword.getText().isEmpty()
                && txtPatronymic.getText().isEmpty())
        {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните все поля");
        }else if(!matcher.matches())
        {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните поле почты в соответсвующем формате");
        }else if(!matcherNumber.matches())
        {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните поле номера телефона в соответсвующем формате");
        }
        else if(value==null){
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните все поля");
        }


//        else if (checkUsername(txtUsername.getText())) {
//            txtUsername.clear();
//            txtPassword.clear();
//            lblErrors.setTextFill(Color.TOMATO);
//            lblErrors.setText("Username Exists!");
//        }
        else {
            RegistrationDto registrationDto = new RegistrationDto();
            registrationDto.setFirstName(txtFirstName.getText());
            registrationDto.setLastName(txtLastName.getText());
            registrationDto.setPatronymic(txtPatronymic.getText());
            registrationDto.setLogin(txtUsername.getText());
            registrationDto.setPassword(txtPassword.getText());
            registrationDto.setPhoneNum(txtPhoneNumber.getText());
            if (checkBox.isSelected()) {
                if(txtPasscode.getText().equals("111")){
                    registrationDto.setRole(ROLE_TYPE.DOCTOR.toString());
                }else if( txtPasscode.getText().equals("222")){
                    registrationDto.setRole(ROLE_TYPE.ADMIN.toString());
                }else {
                    lblErrors.setVisible(true);
                    lblErrors.setTextFill(Color.TOMATO);
                    lblErrors.setText("Неверный код доступа");
                }
            }else {
                registrationDto.setRole(ROLE_TYPE.PATIENT.toString());
            }
            registrationDto.setDateOfBirth(txtDob.getValue().toString());
            ClientRequest<RegistrationDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.register);
            request.setData(registrationDto);

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
                lblErrors.setVisible(true);
                lblErrors.setText(response.getData().toString());
                System.out.println(response.getData());

            } else {
                lblErrors.setVisible(true);
                lblErrors.setText(response.getMessage());
                System.out.println(response.getMessage());
            }

        }
    }

    @FXML
    private void reset() {
        txtPatronymic.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtDob.setValue(null);
        txtPhoneNumber.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtPasscode.clear();
        btnCreate.setDisable(true);
    }

}
