package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.GetDoctorListAtm;
import app.models.DataTransferModels.GetDoctorListDto;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class PatientAppointmentController implements Initializable {

    @FXML
    private TableView<GetDoctorListAtm> myTableView;

    @FXML
    private TableColumn<GetDoctorListAtm, String> idColumnFirstName, idColumnLastName, idColumnPatronymic, idColumnSpecialization,
            idColumnExperience, idColumnRoomNumber, idColumnButton,idColumnID;

    private List<GetDoctorListAtm> doctorsList = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumnID.setCellValueFactory(new PropertyValueFactory<GetDoctorListAtm, String>("id"));
        idColumnFirstName.setCellValueFactory(new PropertyValueFactory<GetDoctorListAtm, String>("first_name"));
        idColumnLastName.setCellValueFactory(new PropertyValueFactory<GetDoctorListAtm, String>("last_name"));
        idColumnPatronymic.setCellValueFactory(new PropertyValueFactory<GetDoctorListAtm, String>("patronymic"));
        idColumnSpecialization.setCellValueFactory(new PropertyValueFactory<GetDoctorListAtm, String>("roomNumber"));
        idColumnExperience.setCellValueFactory(new PropertyValueFactory<GetDoctorListAtm, String>("experience"));
        idColumnRoomNumber.setCellValueFactory(new PropertyValueFactory<GetDoctorListAtm, String>("specialization"));

        myTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (myTableView.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = myTableView.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    int row= tablePosition.getRow();
                    GetDoctorListAtm getDoctorListAtm=myTableView.getItems().get(row);

                    System.out.println("Selected Value" + val);
                    try {
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/availableTime.fxml"));
//                        loader.setLocation(getClass().getResource("availableTime.fxml"));
                        Parent root = loader.load();
                        app.controllers.AvailableTimeController controller = loader.getController();
                        controller.setDoctor(getDoctorListAtm);

                        Scene scene = new Scene(root, 1024, 640);
                        stage.setScene(scene);
                        stage.show();
                        stage.setResizable(false);

                        Stage newStage = (Stage) myTableView.getScene().getWindow();
                        newStage.hide();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        myTableView.getItems().setAll(getItemsToAdd());

    }


    private List<GetDoctorListAtm> getItemsToAdd() {
        GetDoctorListDto getDoctorListDto = new GetDoctorListDto();

        ClientRequest<GetDoctorListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getDoctorsList);
        request.setData(getDoctorListDto);

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
        doctorsList = (List<GetDoctorListAtm>) response.getData();
        return (List<GetDoctorListAtm>) response.getData();
    }


    private void setData() {
        GetDoctorListDto getDoctorListDto = new GetDoctorListDto();

        ClientRequest<GetDoctorListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getDoctorsList);
        request.setData(getDoctorListDto);

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
        List<GetDoctorListAtm> doctorListAtms = (List<GetDoctorListAtm>) response.getData();

    }

    @FXML
    private JFXButton backBtn,commentBtn,appointmentBtn,workScheduleBtn;

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {    ////signout to main page
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
        }

    }

}
