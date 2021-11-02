package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.enums.ROLE_TYPE;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.RegistrationDto;
import app.models.DataTransferModels.UpdateUserByPatientDto;
import app.models.holder.UserHolder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PatientHomeController implements Initializable{

    @FXML
    private JFXTextField txtPhoneNumber, txtUsername, txtLastName, txtFirstName, txtPatronymic,txtDob;

    @FXML
    private JFXButton backBtn,commentBtn,appointmentBtn,workScheduleBtn, saveBtn;

    @FXML
    private Label lblErrors;

    private long id;
    private String phone;
    private String login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {    ////signout to main page
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
        } else if(ae.getSource() == commentBtn) {     /////yes is pressed when patient feels he is cured
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/patientComment.fxml"));
                stage.setScene(new Scene(root, 1024,640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if(ae.getSource() == appointmentBtn) {      ////no is pressed when patient is not cured
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/patientAppointment.fxml"));
                stage.setScene(new Scene(root, 1024,640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else if(ae.getSource()==workScheduleBtn){
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/workingSchedule.fxml"));
                stage.setScene(new Scene(root, 1024,640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else if(ae.getSource()==saveBtn){
            updateUser();
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/patientHome.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
                stage.setResizable(false);

                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void updateUser(){
        if (txtUsername.getText().isEmpty()) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Enter all details");
        }
        else if (txtUsername.getText().equals(login) && txtPhoneNumber.getText().equals(phone)) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Данные не были изменены");
        }
        else {
            UpdateUserByPatientDto updateUserDto = new UpdateUserByPatientDto();
            updateUserDto.setId(id);
            updateUserDto.setLogin(txtUsername.getText());
            updateUserDto.setPhoneNum(txtPhoneNumber.getText());

            ClientRequest<UpdateUserByPatientDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.updateUserByPatient);
            request.setData(updateUserDto);

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
                lblErrors.setText(response.getData().toString());
                System.out.println(response.getData());
            } else {
                lblErrors.setText(response.getMessage());
                System.out.println(response.getMessage());
            }

        }
    }

    private void setData() {

        UserAtm userAtm= UserHolder.getInstance().getUser();

        txtFirstName.setText(userAtm.getFirstName());
        txtLastName.setText(userAtm.getLastName());
        txtPatronymic.setText(userAtm.getPatronymic());
        txtDob.setText(userAtm.getDateOfBirth());
        txtUsername.setText(userAtm.getLogin());
        txtPhoneNumber.setText(userAtm.getPhoneNum());

        login= userAtm.getLogin();
        phone= userAtm.getPhoneNum();
        id= userAtm.getId();
    }
}
