package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.DoctorAppointmentPatientAtm;
import app.models.AnswerTransferModels.GetPatientListAtm;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.DoctorAppointmentPatientDto;
import app.models.DataTransferModels.GetPatientListDto;
import app.models.DataTransferModels.UpdateAppointmentDto;
import app.models.DataTransferModels.UpdateUserByPatientDto;
import app.models.holder.UserHolder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorAppointmentController implements Initializable {

    @FXML
    private JFXButton backBtn, submitBtn;

    @FXML
    private JFXTextField txtHeight, txtWeight, txtDiagnosis, txtRecommendation, txtReport;

    @FXML
    private JFXCheckBox checkBox;
    @FXML
    private Label lblErrors;

    long patientId;

    long appointmentID;

    private static int doneCounter;

    @FXML
    private void onCheckBoxAction() {
        if (checkBox.isSelected()) {
            doneCounter++;
        }
    }

    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
//
//    private void setupTableView() {
//        tableView.setEditable(true);
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            setData();
        });
    }

    private void setData() {

        DoctorAppointmentPatientAtm doctorAppointmentPatientAtm;

        DoctorAppointmentPatientDto doctorAppointmentPatientDto = new DoctorAppointmentPatientDto();
        ClientRequest<DoctorAppointmentPatientDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getPatientAppointment);
        doctorAppointmentPatientDto.setPatientID(patientId);
        doctorAppointmentPatientDto.setAppointmentID(appointmentID);
        request.setData(doctorAppointmentPatientDto);

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
        doctorAppointmentPatientAtm = (DoctorAppointmentPatientAtm) response.getData();

        txtDiagnosis.setText(doctorAppointmentPatientAtm.getDiagnose());
        txtHeight.setText(String.valueOf(doctorAppointmentPatientAtm.getHeight()));
        txtReport.setText(doctorAppointmentPatientAtm.getReport());
        txtRecommendation.setText(doctorAppointmentPatientAtm.getRecommendation());
        txtWeight.setText(String.valueOf(doctorAppointmentPatientAtm.getWeight()));

    }

    private void updateAppointment() {
        if (txtWeight.getText().isEmpty() || txtReport.getText().isEmpty() ||
                txtRecommendation.getText().isEmpty() || txtHeight.getText().isEmpty() ||
                txtDiagnosis.getText().isEmpty()) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Enter all details");
        } else {
            UpdateAppointmentDto updateAppointmentDto = new UpdateAppointmentDto();
            updateAppointmentDto.setAppointmentID(appointmentID);
            updateAppointmentDto.setPatientID(patientId);
            updateAppointmentDto.setDiagnosis(txtDiagnosis.getText());
            updateAppointmentDto.setHeight(Double.parseDouble(txtHeight.getText()));
            updateAppointmentDto.setWeight(Double.parseDouble(txtWeight.getText()));
            updateAppointmentDto.setRecommendation(txtRecommendation.getText());
            if (checkBox.isSelected()) {
                updateAppointmentDto.setVisited(true);
            }

            ClientRequest<UpdateAppointmentDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.updateAppointmentDto);
            request.setData(updateAppointmentDto);

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

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/doctorHome.fxml"));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == submitBtn) {
            updateAppointment();
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/doctorHome.fxml"));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
