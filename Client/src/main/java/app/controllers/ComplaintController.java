package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.DataTransferModels.SendComplaintDto;
import app.models.DataTransferModels.UpdateUserByPatientDto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComplaintController {

    @FXML
    private JFXButton backBtn,commentBtn,appointmentBtn,workScheduleBtn, saveCommentBtn;

    @FXML
    private JFXTextArea textarea;

    @FXML
    private Label lblErrors;

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/patientHome.fxml"));
                stage.setScene(new Scene(root, 1024,640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if(ae.getSource() == appointmentBtn) {
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
        }else if(ae.getSource()==saveCommentBtn){
            sendComment();
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/patientHome.fxml"));
                stage.setScene(new Scene(root, 1024,640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void sendComment(){
        if (textarea.getText().isEmpty()) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Enter details");
        }
        else {
            SendComplaintDto sendComplaintDto = new SendComplaintDto();
            sendComplaintDto.setMessage(textarea.getText());

            ClientRequest<SendComplaintDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.sendComplaint);
            request.setData(sendComplaintDto);

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
                System.out.println(response.getData());
            } else {
                System.out.println(response.getMessage());
            }

        }
    }
}
