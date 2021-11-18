package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.GetPatientListAppointmentAtm;
import app.models.AnswerTransferModels.GetPatientListOfVisitedAppointmentAtm;
import app.models.AnswerTransferModels.GetUserListAtm;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.GetPatientListAppointmentDto;
import app.models.DataTransferModels.GetPatientListOfVisitedAppointmentDto;
import app.models.DataTransferModels.GetUserListDto;
import app.models.holder.UserHolder;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowPatientDataController implements Initializable {

    @FXML
    TableView<GetPatientListOfVisitedAppointmentAtm> tableViewOfVisitedAppointments;

    @FXML
    private TableColumn<GetPatientListOfVisitedAppointmentAtm, String> idColumnIDOfVisitedAppointment,
            idColumnDoctorFirstNameOfVisitedAppointment,
            idColumnDoctorLastNameOfVisitedAppointment,
            idColumnDoctorPatronymicOfVisitedAppointment,
            idColumnSpecializationOfVisitedAppointment,
            idColumnDiagnosisOfVisitedAppointment,
            idColumnRecommendationOfVisitedAppointment,
            idColumnReportOfVisitedAppointment,
            idColumnDateTimeOfVisitedAppointment;

    @FXML
    TableView<GetPatientListAppointmentAtm> tableViewOfAppointments;

    @FXML
    private TableColumn<GetPatientListAppointmentAtm, String> idColumnIDAppointment,
            idColumnDoctorFirstName,
            idColumnDoctorLastName,
            idColumnDoctorPatronymic,
            idColumnSpecialization,
            idColumnDateTime;

    @FXML
    private JFXButton backBtn, commentBtn, appointmentBtn, workScheduleBtn, saveBtn, saveAddressBtn, showDataBtn;

    @FXML
    private Label lblErrors;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumnIDOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentID"));
        idColumnDoctorFirstNameOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentFirst_name"));
        idColumnDoctorLastNameOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentLast_name"));
        idColumnDoctorPatronymicOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentPatronymic"));
        idColumnSpecializationOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentSpecialization"));
        idColumnDiagnosisOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentDiagnosis"));
        idColumnRecommendationOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentRecommendation"));
        idColumnReportOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentReport"));
        idColumnDateTimeOfVisitedAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListOfVisitedAppointmentAtm, String>("visitedAppointmentDateTime"));
        tableViewOfVisitedAppointments.getItems().setAll(getListOfVisitedAppointmentToAdd());

        idColumnIDAppointment.setCellValueFactory(new PropertyValueFactory<GetPatientListAppointmentAtm, String>("appointmentID"));
        idColumnDoctorFirstName.setCellValueFactory(new PropertyValueFactory<GetPatientListAppointmentAtm, String>("first_name"));
        idColumnDoctorLastName.setCellValueFactory(new PropertyValueFactory<GetPatientListAppointmentAtm, String>("last_name"));
        idColumnDoctorPatronymic.setCellValueFactory(new PropertyValueFactory<GetPatientListAppointmentAtm, String>("patronymic"));
        idColumnSpecialization.setCellValueFactory(new PropertyValueFactory<GetPatientListAppointmentAtm, String>("specialization"));
        idColumnDateTime.setCellValueFactory(new PropertyValueFactory<GetPatientListAppointmentAtm, String>("dateTime"));
        tableViewOfAppointments.getItems().setAll(getListOfAppointmentToAdd());
    }

    private List<GetPatientListOfVisitedAppointmentAtm> getListOfVisitedAppointmentToAdd() {
        GetPatientListOfVisitedAppointmentDto getPatientListOfVisitedAppointmentDto=new GetPatientListOfVisitedAppointmentDto();
        UserAtm userAtm = UserHolder.getInstance().getUser();
        getPatientListOfVisitedAppointmentDto.setUserID(userAtm.getId());

        ClientRequest<GetPatientListOfVisitedAppointmentDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getPatientListOfVisitedAppointment);
        request.setData(getPatientListOfVisitedAppointmentDto);

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
        return (List<GetPatientListOfVisitedAppointmentAtm>) response.getData();
    }

    private List<GetPatientListAppointmentAtm> getListOfAppointmentToAdd() {
        GetPatientListAppointmentDto getPatientListAppointmentDto=new GetPatientListAppointmentDto();
        UserAtm userAtm = UserHolder.getInstance().getUser();
        getPatientListAppointmentDto.setUserID(userAtm.getId());

        ClientRequest<GetPatientListAppointmentDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getPatientListAppointment);
        request.setData(getPatientListAppointmentDto);

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
        return (List<GetPatientListAppointmentAtm>) response.getData();
    }

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {    ////signout to main page
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/main.fxml"));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == commentBtn) {     /////yes is pressed when patient feels he is cured
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/complaint.fxml"));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == appointmentBtn) {      ////no is pressed when patient is not cured
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/patientAppointment.fxml"));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == workScheduleBtn) {
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/workingSchedule.fxml"));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ae.getSource() == saveBtn) {
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
        } else if (ae.getSource() == saveAddressBtn) {
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
        } else if (ae.getSource() == showDataBtn) {
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/PatientAppointmentData.fxml"));
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
