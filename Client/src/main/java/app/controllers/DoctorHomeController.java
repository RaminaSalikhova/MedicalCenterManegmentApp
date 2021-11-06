package app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorHomeController {

    @FXML
    private JFXTextField txtPhoneNumber, txtUsername, txtLastName, txtFirstName, txtPatronymic,txtDob;

    @FXML
    private JFXButton backBtn,commentBtn,appointmentBtn,workScheduleBtn, saveBtn;

    @FXML
    private Label lblErrors;

    private long id;
    private String phone;
    private String login;


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
}
