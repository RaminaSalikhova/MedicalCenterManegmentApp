package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.GetDistrictsAndAddressesForAdminAtm;
import app.models.AnswerTransferModels.GetDoctorListForAdminAtm;
import app.models.AnswerTransferModels.GetUserListAtm;
import app.models.DataTransferModels.GetDistrictsAndAddressesForAdminDto;
import app.models.DataTransferModels.GetDoctorListForAdminDto;
import app.models.DataTransferModels.GetUserListDto;
import com.jfoenix.controls.JFXButton;
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

public class DoctorEditController implements Initializable {

    @FXML
    private JFXButton backBtn, saveReportBtn;

    @FXML
    TableView<GetDoctorListForAdminAtm> tableViewDoctors;


    @FXML
    private TableColumn<GetDoctorListForAdminAtm, String> idColumnIDUser, idColumnIDDoctor, idColumnFirstName,idColumnLastName, idColumnPatronymic, idColumnPhoneNum,
            idColumnFrom, idColumnTill, idColumnRoom,
    idColumnSpecialization,idColumnDistrict, idColumnSalary,idColumnExperience;

    @FXML
    TableView<GetDistrictsAndAddressesForAdminAtm> tableViewDistricts;


    @FXML
    private TableColumn<GetDistrictsAndAddressesForAdminAtm, String> idColumnIDDistrict,
            idColumnIDAddress, idColumnIDDistrictName, idColumnAddressName, idColumnAddressHouse, idColumnAddressFlat;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumnIDUser.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("userID"));
        idColumnIDDoctor.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("doctorID"));
        idColumnLastName.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("lastName"));
        idColumnPatronymic.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("patronymic"));
        idColumnFirstName.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("firstName"));
        idColumnPhoneNum.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("phoneNum"));
        idColumnFrom.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("fromWorkTime"));
        idColumnTill.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("tillWorkTime"));
        idColumnRoom.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("roomNumber"));
        idColumnSpecialization.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("specialization"));
        idColumnDistrict.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("districtName"));
        idColumnSalary.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("salary"));
        idColumnExperience.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("experience"));

        tableViewDoctors.getItems().setAll(getDoctorsToAdd());


        idColumnIDDistrict.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("districtID"));
        idColumnIDAddress.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("addressID"));
        idColumnIDDistrictName.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("districtName"));
        idColumnAddressName.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("addressName"));
        idColumnAddressHouse.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("addressHouse"));
        idColumnAddressFlat.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("addressFlat"));

        tableViewDistricts.getItems().setAll(getDistrictsToAdd());

    }

    private List<GetDistrictsAndAddressesForAdminAtm> getDistrictsToAdd(){
        GetDistrictsAndAddressesForAdminDto getDistrictsAndAddressesForAdminDto = new GetDistrictsAndAddressesForAdminDto();

        ClientRequest<GetDistrictsAndAddressesForAdminDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getDistrictsForAdmin);
        request.setData(getDistrictsAndAddressesForAdminDto);

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
        return (List<GetDistrictsAndAddressesForAdminAtm>) response.getData();
    }

    private List<GetDoctorListForAdminAtm> getDoctorsToAdd(){
        GetDoctorListForAdminDto getDoctorListForAdminDto = new GetDoctorListForAdminDto();

        ClientRequest<GetDoctorListForAdminDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getDistrictsForAdmin);
        request.setData(getDoctorListForAdminDto);

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
        return (List<GetDoctorListForAdminAtm>) response.getData();
    }

    private void saveReport(){

    }

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) {
        if (ae.getSource() == backBtn) {
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/adminHome.fxml"));
                stage.setScene(new Scene(root, 1024, 640));
                stage.show();
                stage.setResizable(false);


                ((Node) (ae.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if(ae.getSource() == saveReportBtn){
            saveReport();
            try {
                Stage stage = new Stage();
                Pane root = FXMLLoader.load(getClass().getResource("/doctorEdit.fxml"));
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
