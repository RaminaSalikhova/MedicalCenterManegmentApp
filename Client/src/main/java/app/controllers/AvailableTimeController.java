package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.GetAvailableTimeListAtm;
import app.models.AnswerTransferModels.GetDoctorListAtm;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.GetAvailableTimeListDto;
import app.models.DataTransferModels.GetDoctorListDto;
import app.models.DataTransferModels.SetAppointmentDto;
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
import javafx.scene.Parent;
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

public class AvailableTimeController implements Initializable {
    @FXML
    private TableView<GetAvailableTimeListAtm> tableView;

    @FXML
    private TableColumn<GetAvailableTimeListAtm, String> idColumnID, idColumnDay, idColumnTime;


    GetDoctorListAtm doctor;

    public void setDoctor(GetDoctorListAtm doctor) {
        this.doctor = doctor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
//            List<GetAvailableTimeListAtm> getAvailableTimeListAtms=getItemsToAdd();
            idColumnID.setCellValueFactory(new PropertyValueFactory<GetAvailableTimeListAtm, String>("id"));
            idColumnDay.setCellValueFactory(new PropertyValueFactory<GetAvailableTimeListAtm, String>("date"));
            idColumnTime.setCellValueFactory(new PropertyValueFactory<GetAvailableTimeListAtm, String>("time"));

            tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    //Check whether item is selected and set value of selected item to Label
                    if (tableView.getSelectionModel().getSelectedItem() != null) {
                        TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
                        ObservableList selectedCells = selectionModel.getSelectedCells();
                        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                        Object val = tablePosition.getTableColumn().getCellData(newValue);
                        int row= tablePosition.getRow();
                        GetAvailableTimeListAtm getAvailableTimeListAtm=tableView.getItems().get(row);

                        System.out.println("Selected Value" + val);
                        UserAtm userAtm= UserHolder.getInstance().getUser();
                        setAppointment(userAtm.getId(), getAvailableTimeListAtm);
                        try {
                            Stage stage = new Stage();
                            Pane root = FXMLLoader.load(getClass().getResource("/patientHome.fxml"));
                            stage.setScene(new Scene(root));
                            stage.show();
                            stage.setResizable(false);

                            Stage newStage = (Stage) tableView.getScene().getWindow();
                            newStage.hide();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            });
            tableView.getItems().setAll(getItemsToAdd());

        });

    }

    private void setAppointment(long id, GetAvailableTimeListAtm getAvailableTimeListAtm){
        SetAppointmentDto setAppointmentDto = new SetAppointmentDto();
        setAppointmentDto.setUserID(id);
        setAppointmentDto.setDoctorID(doctor.getId());
        setAppointmentDto.setDate(getAvailableTimeListAtm.getDate());
        setAppointmentDto.setTime(getAvailableTimeListAtm.getTime());

        ClientRequest<SetAppointmentDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.setAppointment);
        request.setData(setAppointmentDto);

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

    private List<GetAvailableTimeListAtm> getItemsToAdd() {
        GetAvailableTimeListDto getAvailableTimeListdto = new GetAvailableTimeListDto();
        getAvailableTimeListdto.setId(doctor.getId());

        ClientRequest<GetAvailableTimeListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getAvailableTime);
        request.setData(getAvailableTimeListdto);

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
        return (List<GetAvailableTimeListAtm>) response.getData();
    }


    @FXML
    private JFXButton backBtn,commentBtn,appointmentBtn,workScheduleBtn, showDataBtn;

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {    ////signout to main page
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
        }else if (ae.getSource() == showDataBtn) {
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
