package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.*;
import app.models.DataTransferModels.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorEditController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private JFXButton backBtn, saveReportBtn, addAddressBtn, addDistrictBtn;

    @FXML
    private JFXTextField txtDistrict, txtName, txtHouse, txtFlat, txtDistrictName;
    @FXML
    TableView<GetDoctorListForAdminAtm> tableViewDoctors;


    @FXML
    private TableColumn<GetDoctorListForAdminAtm, String> idColumnIDUser, idColumnIDDoctor, idColumnFirstName, idColumnLastName, idColumnPatronymic, idColumnPhoneNum,
            idColumnFrom, idColumnTill, idColumnRoom,
            idColumnSpecialization, idColumnDistrict, idColumnSalary, idColumnExperience;

    @FXML
    TableView<GetDistrictsAndAddressesForAdminAtm> tableViewDistricts;


    @FXML
    private TableColumn<GetDistrictsAndAddressesForAdminAtm, String> idColumnIDDistrict,
            idColumnIDAddress, idColumnIDDistrictName, idColumnAddressName, idColumnAddressHouse, idColumnAddressFlat;

    @FXML
    TableView<GetDistrictsForAdminAtm> tableViewDistrictsName;


    @FXML
    private TableColumn<GetDistrictsForAdminAtm, String> idColumnIDDistrictForDistrict,
            idColumnIDDistrictNameForDistrict;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewDoctors.setEditable(true);
        tableViewDistricts.setEditable(true);

        idColumnIDUser.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("userID"));
        idColumnIDDoctor.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("doctorID"));
        idColumnLastName.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("lastName"));
        idColumnPatronymic.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("patronymic"));
        idColumnFirstName.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("firstName"));
        idColumnPhoneNum.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("phoneNum"));
        idColumnFrom.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("fromWorkTime"));
        idColumnFrom.setCellFactory(TextFieldTableCell.<GetDoctorListForAdminAtm>forTableColumn());
        idColumnFrom.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDoctorListForAdminAtm, String> event) -> {
                    TablePosition<GetDoctorListForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDoctorListForAdminAtm getDoctorListForAdminAtm = event.getTableView().getItems().get(row);
                    getDoctorListForAdminAtm.setFromWorkTime(newValue);
                    updateDoctor(getDoctorListForAdminAtm);
                });

        idColumnTill.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("tillWorkTime"));
        idColumnTill.setCellFactory(TextFieldTableCell.<GetDoctorListForAdminAtm>forTableColumn());
        idColumnTill.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDoctorListForAdminAtm, String> event) -> {
                    TablePosition<GetDoctorListForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDoctorListForAdminAtm getDoctorListForAdminAtm = event.getTableView().getItems().get(row);
                    getDoctorListForAdminAtm.setTillWorkTime(newValue);
                    updateDoctor(getDoctorListForAdminAtm);

                });

        idColumnRoom.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("roomNumber"));
        idColumnRoom.setCellFactory(TextFieldTableCell.<GetDoctorListForAdminAtm>forTableColumn());
        idColumnRoom.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDoctorListForAdminAtm, String> event) -> {
                    TablePosition<GetDoctorListForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDoctorListForAdminAtm getDoctorListForAdminAtm = event.getTableView().getItems().get(row);
                    getDoctorListForAdminAtm.setRoomNumber(newValue);
                    updateDoctor(getDoctorListForAdminAtm);

                });

        idColumnSpecialization.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("specialization"));
        idColumnSpecialization.setCellFactory(TextFieldTableCell.<GetDoctorListForAdminAtm>forTableColumn());
        idColumnSpecialization.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDoctorListForAdminAtm, String> event) -> {
                    TablePosition<GetDoctorListForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDoctorListForAdminAtm getDoctorListForAdminAtm = event.getTableView().getItems().get(row);
                    getDoctorListForAdminAtm.setSpecialization(newValue);
                    updateDoctor(getDoctorListForAdminAtm);
                });

        idColumnDistrict.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("districtName"));
        idColumnDistrict.setCellFactory(TextFieldTableCell.<GetDoctorListForAdminAtm>forTableColumn());
        idColumnDistrict.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDoctorListForAdminAtm, String> event) -> {
                    TablePosition<GetDoctorListForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDoctorListForAdminAtm getDoctorListForAdminAtm = event.getTableView().getItems().get(row);
                    getDoctorListForAdminAtm.setDistrictName(newValue);
                    updateDoctor(getDoctorListForAdminAtm);
                });

        idColumnSalary.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("salary"));
        idColumnSalary.setCellFactory(TextFieldTableCell.<GetDoctorListForAdminAtm>forTableColumn());
        idColumnSalary.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDoctorListForAdminAtm, String> event) -> {
                    TablePosition<GetDoctorListForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDoctorListForAdminAtm getDoctorListForAdminAtm = event.getTableView().getItems().get(row);
                    getDoctorListForAdminAtm.setSalary(newValue);
                    updateDoctor(getDoctorListForAdminAtm);
                });

        idColumnExperience.setCellValueFactory(new PropertyValueFactory<GetDoctorListForAdminAtm, String>("experience"));
        idColumnExperience.setCellFactory(TextFieldTableCell.<GetDoctorListForAdminAtm>forTableColumn());
        idColumnExperience.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDoctorListForAdminAtm, String> event) -> {
                    TablePosition<GetDoctorListForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDoctorListForAdminAtm getDoctorListForAdminAtm = event.getTableView().getItems().get(row);
                    getDoctorListForAdminAtm.setExperience(newValue);
                    updateDoctor(getDoctorListForAdminAtm);

                });

        tableViewDoctors.getItems().setAll(getDoctorsToAdd());


        idColumnIDDistrict.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("districtID"));
        idColumnIDAddress.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("addressID"));

        idColumnIDDistrictName.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("districtName"));
        idColumnIDDistrictName.setCellFactory(TextFieldTableCell.<GetDistrictsAndAddressesForAdminAtm>forTableColumn());
        idColumnIDDistrictName.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDistrictsAndAddressesForAdminAtm, String> event) -> {
                    TablePosition<GetDistrictsAndAddressesForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDistrictsAndAddressesForAdminAtm getDistrictsAndAddressesForAdminAtm = event.getTableView().getItems().get(row);
                    getDistrictsAndAddressesForAdminAtm.setDistrictName(newValue);
                    updateDistrict(getDistrictsAndAddressesForAdminAtm);

                });

        idColumnAddressName.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("addressName"));
        idColumnAddressName.setCellFactory(TextFieldTableCell.<GetDistrictsAndAddressesForAdminAtm>forTableColumn());
        idColumnAddressName.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDistrictsAndAddressesForAdminAtm, String> event) -> {
                    TablePosition<GetDistrictsAndAddressesForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDistrictsAndAddressesForAdminAtm getDistrictsAndAddressesForAdminAtm = event.getTableView().getItems().get(row);
                    getDistrictsAndAddressesForAdminAtm.setAddressName(newValue);
                    updateDistrict(getDistrictsAndAddressesForAdminAtm);

                });

        idColumnAddressHouse.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("addressHouse"));
        idColumnAddressHouse.setCellFactory(TextFieldTableCell.<GetDistrictsAndAddressesForAdminAtm>forTableColumn());
        idColumnAddressHouse.setOnEditCommit(
                (TableColumn.CellEditEvent<GetDistrictsAndAddressesForAdminAtm, String> event) -> {
                    TablePosition<GetDistrictsAndAddressesForAdminAtm, String> pos = event.getTablePosition();

                    String newValue = event.getNewValue();

                    int row = pos.getRow();
                    GetDistrictsAndAddressesForAdminAtm getDistrictsAndAddressesForAdminAtm = event.getTableView().getItems().get(row);
                    getDistrictsAndAddressesForAdminAtm.setAddressHouse(newValue);
                    updateDistrict(getDistrictsAndAddressesForAdminAtm);

                });

//        idColumnAddressFlat.setCellValueFactory(new PropertyValueFactory<GetDistrictsAndAddressesForAdminAtm, String>("addressFlat"));
//        idColumnAddressFlat.setCellFactory(TextFieldTableCell.<GetDistrictsAndAddressesForAdminAtm>forTableColumn());
//        idColumnAddressFlat.setOnEditCommit(
//                (TableColumn.CellEditEvent<GetDistrictsAndAddressesForAdminAtm, String> event) -> {
//                    TablePosition<GetDistrictsAndAddressesForAdminAtm, String> pos = event.getTablePosition();
//
//                    String newValue = event.getNewValue();
//
//                    int row = pos.getRow();
//                    GetDistrictsAndAddressesForAdminAtm getDistrictsAndAddressesForAdminAtm = event.getTableView().getItems().get(row);
//                    getDistrictsAndAddressesForAdminAtm.setAddressFlat(newValue);
//                    updateDistrict(getDistrictsAndAddressesForAdminAtm);
//
//                });

        tableViewDistricts.getItems().setAll(getDistrictsToAdd());


        idColumnIDDistrictForDistrict.setCellValueFactory(new PropertyValueFactory<GetDistrictsForAdminAtm, String>("districtID"));

        idColumnIDDistrictNameForDistrict.setCellValueFactory(new PropertyValueFactory<GetDistrictsForAdminAtm, String>("name"));
//        idColumnIDDistrictNameForDistrict.setCellFactory(TextFieldTableCell.<GetDistrictsAndAddressesForAdminAtm>forTableColumn());
//        idColumnIDDistrictNameForDistrict.setOnEditCommit(
//                (TableColumn.CellEditEvent<GetDistrictsAndAddressesForAdminAtm, String> event) -> {
//                    TablePosition<GetDistrictsAndAddressesForAdminAtm, String> pos = event.getTablePosition();
//
//                    String newValue = event.getNewValue();
//
//                    int row = pos.getRow();
//                    GetDistrictsAndAddressesForAdminAtm getDistrictsAndAddressesForAdminAtm = event.getTableView().getItems().get(row);
//                    getDistrictsAndAddressesForAdminAtm.setDistrictName(newValue);
//                    updateDistrict(getDistrictsAndAddressesForAdminAtm);
//
//                });
        tableViewDistrictsName.getItems().setAll(getDistrictsNameToAdd());
    }
    private List<GetDistrictsForAdminAtm> getDistrictsNameToAdd() {
        GetDistrictsForAdminDto getDistrictsForAdminDto = new GetDistrictsForAdminDto();

        ClientRequest<GetDistrictsForAdminDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getDistrictsNameForAdmin);
        request.setData(getDistrictsForAdminDto);

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
        return (List<GetDistrictsForAdminAtm>) response.getData();
    }

    private UpdateDoctorDto updateDoctorDtoMapping(GetDoctorListForAdminAtm getDoctorListForAdminAtm){
        UpdateDoctorDto updateDoctorDto = new UpdateDoctorDto();

        updateDoctorDto.setUserID(getDoctorListForAdminAtm.getUserID());
        updateDoctorDto.setDoctorID(getDoctorListForAdminAtm.getDoctorID());
        updateDoctorDto.setLastName(getDoctorListForAdminAtm.getLastName());
        updateDoctorDto.setPatronymic(getDoctorListForAdminAtm.getPatronymic());
        updateDoctorDto.setFirstName(getDoctorListForAdminAtm.getFirstName());
        updateDoctorDto.setPhoneNum(getDoctorListForAdminAtm.getPhoneNum());
        updateDoctorDto.setFromWorkTime(getDoctorListForAdminAtm.getFromWorkTime());
        updateDoctorDto.setTillWorkTime(getDoctorListForAdminAtm.getTillWorkTime());
        updateDoctorDto.setDistrictName(getDoctorListForAdminAtm.getDistrictName());
        updateDoctorDto.setRoomNumber(getDoctorListForAdminAtm.getRoomNumber());
        updateDoctorDto.setSpecialization(getDoctorListForAdminAtm.getSpecialization());
        updateDoctorDto.setSalary(getDoctorListForAdminAtm.getSalary());
        updateDoctorDto.setExperience(getDoctorListForAdminAtm.getExperience());

        return updateDoctorDto;
    }

    private void updateDoctor(GetDoctorListForAdminAtm getDoctorListForAdminAtm) {

        ClientRequest<UpdateDoctorDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.updateDoctor);
        request.setData(updateDoctorDtoMapping(getDoctorListForAdminAtm));

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
            lblErrors.setText(response.getData().toString());
        } else {
            System.out.println(response.getMessage());
        }
        reload();
    }

    private UpdateDistrictDto updateDistrictDtoMapping(GetDistrictsAndAddressesForAdminAtm getDistrictsAndAddressesForAdminAtm){
        UpdateDistrictDto updateDistrictDto=new UpdateDistrictDto();
        updateDistrictDto.setDistrictID(getDistrictsAndAddressesForAdminAtm.getDistrictID());
        updateDistrictDto.setAddressID(getDistrictsAndAddressesForAdminAtm.getAddressID());
        updateDistrictDto.setDistrictName(getDistrictsAndAddressesForAdminAtm.getDistrictName());
        updateDistrictDto.setAddressName(getDistrictsAndAddressesForAdminAtm.getAddressName());
        updateDistrictDto.setAddressHouse(getDistrictsAndAddressesForAdminAtm.getAddressHouse());
        updateDistrictDto.setAddressFlat(getDistrictsAndAddressesForAdminAtm.getAddressFlat());

        return updateDistrictDto;
    }

    private void updateDistrict(GetDistrictsAndAddressesForAdminAtm getDistrictsAndAddressesForAdminAtm) {
        ClientRequest<UpdateDistrictDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.updateDistrict);
        request.setData(updateDistrictDtoMapping(getDistrictsAndAddressesForAdminAtm));

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
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText(response.getData().toString());
        } else {
            System.out.println(response.getMessage());
        }
        reload();
    }

    private List<GetDistrictsAndAddressesForAdminAtm> getDistrictsToAdd() {
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

    private List<GetDoctorListForAdminAtm> getDoctorsToAdd() {
        GetDoctorListForAdminDto getDoctorListForAdminDto = new GetDoctorListForAdminDto();

        ClientRequest<GetDoctorListForAdminDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getDoctorsForAdmin);
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

    private void reload(){
        try {
            Stage stage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("/doctorEdit.fxml"));
            stage.setScene(new Scene(root, 1024, 640));
            stage.show();
            stage.setResizable(false);

            Stage newStage = (Stage) tableViewDistricts.getScene().getWindow();
            newStage.hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveReport() {
        try {
            String content;
            GetReportDto getReportDto = new GetReportDto();

            ClientRequest<GetReportDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.getReport);
            request.setData(getReportDto);

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
                content= (String) response.getData();
            } else {
                System.out.println(response.getMessage());
                content= (String) response.getMessage();
            }
//            Path fileName = Path.of("Districts.txt");

//            String content  = "Отчет по загруженности участков\n"
//                    +"Количество участков: "+ getDistrictsToAdd().size() +";\n";
//            int amountOfDoctorsPerDistrict=0;
//            List<GetDoctorListForAdminAtm> list=getDoctorsToAdd();
//            for(GetDoctorListForAdminAtm el: list){
//                if()
//                amountOfDoctorsPerDistrict++;
//                content  = content.concat("Участок: "+ el.getDistrictName());
//            }
//            content  = content.concat("Участок: ");
            FileWriter writer = new FileWriter("Districts.txt", false);
            writer.write(content);
            writer.flush();
//            Files.writeString(fileName, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addAddress(){
        AddAddressDto addAddress=new AddAddressDto();
        addAddress.setDistrictName(txtDistrict.getText());
        addAddress.setAddressName(txtName.getText());
        addAddress.setAddressHouse(txtHouse.getText());
//        addAddress.setAddressFlat(txtFlat.getText());

        ClientRequest<AddAddressDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.addAddress);
        request.setData(addAddress);

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
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText(response.getData().toString());
        } else {
            System.out.println(response.getMessage());
        }
        reload();
    }

    private void addDistrict(){
        AddDistrictDto addDistrictDto=new AddDistrictDto();
        addDistrictDto.setDistrictName(txtDistrictName.getText());

        ClientRequest<AddDistrictDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.addDistrict);
        request.setData(addDistrictDto);

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
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText(response.getData().toString());
        } else {
            System.out.println(response.getMessage());
        }
        reload();
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
        } else if (ae.getSource() == saveReportBtn) {
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
        }else if (ae.getSource() == addAddressBtn) {
            addAddress();

        }else if (ae.getSource() == addDistrictBtn) {
            addDistrict();

        }
    }
}
