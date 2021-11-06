package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.GetAvailableTimeListAtm;
import app.models.AnswerTransferModels.GetDoctorListAtm;
import app.models.AnswerTransferModels.GetScheduleListAtm;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.GetAvailableTimeListDto;
import app.models.DataTransferModels.GetScheduleListDto;
import app.models.holder.UserHolder;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WorkingScheduleController implements Initializable {

    @FXML
    private TableView<GetScheduleListAtm> tableView;

    @FXML
    private TableColumn<GetScheduleListAtm, String> idColumnID, idColumnFirst_name,
            idColumnLast_name, idColumnStartTime, idColumnEndTime, idColumnRoomNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumnFirst_name.setCellValueFactory(new PropertyValueFactory<GetScheduleListAtm, String>("first_name"));
        idColumnLast_name.setCellValueFactory(new PropertyValueFactory<GetScheduleListAtm, String>("last_name"));
        idColumnStartTime.setCellValueFactory(new PropertyValueFactory<GetScheduleListAtm, String>("startTime"));
        idColumnEndTime.setCellValueFactory(new PropertyValueFactory<GetScheduleListAtm, String>("endTime"));
        idColumnRoomNumber.setCellValueFactory(new PropertyValueFactory<GetScheduleListAtm, String>("roomNumber"));

        tableView.getItems().setAll(getItemsToAdd());
    }

    private List<GetScheduleListAtm> getItemsToAdd() {
        GetScheduleListDto getScheduleListDto = new GetScheduleListDto();

        ClientRequest<GetScheduleListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getSchedule);
        request.setData(getScheduleListDto);

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
        return (List<GetScheduleListAtm>) response.getData();
    }


    @FXML
    private JFXButton backBtn, commentBtn, appointmentBtn, workScheduleBtn;


    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {    ////signout to main page
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/patientHome.fxml"));
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
                Pane root = FXMLLoader.load(getClass().getResource("/patientComment.fxml"));
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
        }
    }


}
