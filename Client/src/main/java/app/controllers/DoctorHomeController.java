package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.*;
import app.models.DataTransferModels.GetPatientListDto;
import app.models.DataTransferModels.GetScheduleListDto;
import app.models.holder.UserHolder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorHomeController implements Initializable {

    @FXML
    private JFXButton backBtn;

    @FXML
    TableView<GetPatientListAtm> tableView;

    @FXML
    private TableColumn<GetPatientListAtm, String> idColumnID, idColumnFirstName, idColumnLastName, idColumnPatronymic,
            idColumnAddress, idColumnSex, idColumnTime, idColumnVisited, idColumnIDpatient;

    @FXML
    private Label lblErrors;

    @FXML
    PieChart chart;

    private final static int initialCount = getItemsToAddSize();

    private static int doneCounter;

    public static void incrementDoneCount() {
        doneCounter++;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idColumnID.setCellValueFactory(new PropertyValueFactory<GetPatientListAtm, String>("appointmentID"));
        idColumnIDpatient.setCellValueFactory(new PropertyValueFactory<GetPatientListAtm, String>("patientID"));
        idColumnFirstName.setCellValueFactory(new PropertyValueFactory<GetPatientListAtm, String>("first_name"));
        idColumnLastName.setCellValueFactory(new PropertyValueFactory<GetPatientListAtm, String>("last_name"));
        idColumnPatronymic.setCellValueFactory(new PropertyValueFactory<GetPatientListAtm, String>("patronymic"));
        idColumnAddress.setCellValueFactory(new PropertyValueFactory<GetPatientListAtm, String>("address"));
        idColumnSex.setCellValueFactory(new PropertyValueFactory<GetPatientListAtm, String>("sex"));
        idColumnTime.setCellValueFactory(new PropertyValueFactory<GetPatientListAtm, String>("time"));

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    int row = tablePosition.getRow();
                    GetPatientListAtm getPatientListAtm = tableView.getItems().get(row);

                    System.out.println("Selected Value" + val);
                    try {
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/doctorAppointment.fxml"));
//                        loader.setLocation(getClass().getResource("availableTime.fxml"));
                        Parent root = loader.load();
                        app.controllers.DoctorAppointmentController controller = loader.getController();
                        controller.setPatientId(getPatientListAtm.getPatientID());
                        controller.setAppointmentID(getPatientListAtm.getAppointmentID());

                        Scene scene = new Scene(root, 1024, 640);
                        stage.setScene(scene);
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


        int percent;
        if (doneCounter == 0) {
            percent = 100;
        } else {
            if (initialCount != 0) {
                percent = doneCounter * 100 / initialCount;
            } else {
                percent = 100;
            }
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Осталось", 100-percent),
                        new PieChart.Data("Выполнено", percent));

        chart.setData(pieChartData);

    }

    private List<GetPatientListAtm> getItemsToAdd() {
        GetPatientListDto getPatientListDto = new GetPatientListDto();

        ClientRequest<GetPatientListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getPatientList);
        UserAtm userAtm = UserHolder.getInstance().getUser();
        getPatientListDto.setUserID(userAtm.getId());
        request.setData(getPatientListDto);

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
        List<GetPatientListAtm> listAtms = (List<GetPatientListAtm>) response.getData();
        return (List<GetPatientListAtm>) response.getData();
    }

    private static int getItemsToAddSize() {
        GetPatientListDto getPatientListDto = new GetPatientListDto();

        ClientRequest<GetPatientListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getPatientList);
        UserAtm userAtm = UserHolder.getInstance().getUser();
        getPatientListDto.setUserID(userAtm.getId());
        request.setData(getPatientListDto);

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
        List<GetPatientListAtm> listAtms = (List<GetPatientListAtm>) response.getData();
        return listAtms.size();
    }

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {
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
        }
    }
}
