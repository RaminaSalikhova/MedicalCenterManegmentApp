package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.enums.ROLE_TYPE;
import app.models.AnswerTransferModels.GetAddressListAtm;
import app.models.AnswerTransferModels.GetDistrictsAndAddressesForAdminAtm;
import app.models.AnswerTransferModels.GetUserAddressAtm;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.*;
import app.models.holder.UserHolder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientHomeController implements Initializable {

    @FXML
    private JFXTextField txtPhoneNumber, txtUsername, txtLastName, txtFirstName, txtPatronymic, txtDob,txtFlat;

    @FXML
    private JFXButton backBtn, commentBtn, appointmentBtn, workScheduleBtn, saveBtn, saveAddressBtn, showDataBtn;

    @FXML
    private Label lblErrors;

    @FXML
    private ComboBox comboAddress;

    private long id;
    private String phone;
    private String login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboAddress.getItems().addAll(getAddressToAdd());

        setData();
    }

    private List<String> getItemsToAdd() {
        GetAddressListDto getAddressListDto = new GetAddressListDto();

        ClientRequest<GetAddressListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getAddressList);
        request.setData(getAddressListDto);

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

        List<GetAddressListAtm> getAddressListAtms = (List<GetAddressListAtm>) response.getData();
        List<String> elements = new ArrayList<>();
        for (GetAddressListAtm el : getAddressListAtms) {
//            elements.add("Улица: " + el.getAddressName() + " Дом: " + el.getAddressHouse() + " Квартира: " + el.getAddressFlat());
            elements.add("Улица: " + el.getAddressName() + " Дом: " + el.getAddressHouse() );

        }
        return elements;
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
            updateUser();
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
            saveAddress();
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

    private void saveAddress() {
        if (txtFlat.getText().isEmpty() &&
        comboAddress.getSelectionModel().isEmpty()){
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните все поля");
        }else {
            UpdateUserAddressDto updateUserAddressDto = new UpdateUserAddressDto();
            updateUserAddressDto.setUserID(id);

            String el = (String) comboAddress.getSelectionModel().getSelectedItem();
            String[] values = el.split(" ");
            String addressName = values[1];
            String house = values[3];
            String flat = txtFlat.getText();

            updateUserAddressDto.setAddressName(addressName);
            updateUserAddressDto.setHouse(house);
            updateUserAddressDto.setFlat(flat);

            ClientRequest<UpdateUserAddressDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.updateUserAddress);
            request.setData(updateUserAddressDto);

            try {
                ClientConnection.getInstance().sendObject(request);
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            ServerResponse response = null;
            try {
                response = (ServerResponse) ClientConnection.getInstance().receiveObject();
            } catch (ClassNotFoundException |
                    IOException e) {
                e.printStackTrace();
            }
            if (response.getStatus() == true) {
                System.out.println(response.getData());
            } else {
                System.out.println(response.getMessage());
            }
        }
    }

    private void updateUser() {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txtUsername.getText());

        String numberRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern patterNumber = Pattern.compile(numberRegex);
        Matcher matcherNumber = patterNumber.matcher(txtPhoneNumber.getText());

        if (txtUsername.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()) {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Введите все данные");
        } else if (txtUsername.getText().equals(login) && txtPhoneNumber.getText().equals(phone)) {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Данные не были изменены");
        } else if (!matcher.matches()) {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните поле почты в соответсвующем формате");
        } else if (!matcherNumber.matches()) {
            lblErrors.setVisible(true);
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Заполните поле номера телефона в соответсвующем формате");
        } else {
            UpdateUserByPatientDto updateUserDto = new UpdateUserByPatientDto();
            updateUserDto.setId(id);
            updateUserDto.setLogin(txtUsername.getText());
            updateUserDto.setPhoneNum(txtPhoneNumber.getText());

            ClientRequest<UpdateUserByPatientDto> request = new ClientRequest<>();
            request.setType(HANDLER_TYPE.updateUserByPatient);
            request.setData(updateUserDto);

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
                lblErrors.setText(response.getData().toString());
                System.out.println(response.getData());
            } else {
                lblErrors.setText(response.getMessage());
                System.out.println(response.getMessage());
            }

        }
    }

    private void setData() {

        UserAtm userAtm = UserHolder.getInstance().getUser();

        txtFirstName.setText(userAtm.getFirstName());
        txtLastName.setText(userAtm.getLastName());
        txtPatronymic.setText(userAtm.getPatronymic());
        txtDob.setText(userAtm.getDateOfBirth());
        txtUsername.setText(userAtm.getLogin());
        txtPhoneNumber.setText(userAtm.getPhoneNum());

        login = userAtm.getLogin();
        phone = userAtm.getPhoneNum();
        id = userAtm.getId();

        GetUserAddressDto getUserAddressDto = new GetUserAddressDto();
        getUserAddressDto.setUserID(id);
        ClientRequest<GetUserAddressDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getUserAddress);
        request.setData(getUserAddressDto);

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

        Optional<GetUserAddressAtm> getUserAddressAtm = Optional.ofNullable((GetUserAddressAtm) response.getData());
        Optional<String> name=Optional.ofNullable(getUserAddressAtm.get().getAddressName());
        if (name.isEmpty()) {
            comboAddress.setPromptText("Выберите адрес");
        } else {
            String address = "Улица: " + getUserAddressAtm.get().getAddressName() + " Дом: " + getUserAddressAtm.get().getAddressHouse()
                    + " Квартира: " + getUserAddressAtm.get().getAddressFlat();
            comboAddress.setPromptText(address);
            comboAddress.setDisable(true);
            saveAddressBtn.setDisable(true);
            txtFlat.setDisable(true);
        }
    }

    private List<String> getAddressToAdd() {
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

        List<GetDistrictsAndAddressesForAdminAtm> getAddressListAtms = (List<GetDistrictsAndAddressesForAdminAtm>) response.getData();
        List<String> elements = new ArrayList<>();
        for (GetDistrictsAndAddressesForAdminAtm el : getAddressListAtms) {
//            elements.add("Улица: " + el.getAddressName() + " Дом: " + el.getAddressHouse() + " Квартира: " + el.getAddressFlat());
            elements.add("Улица: " + el.getAddressName() + " Дом: " + el.getAddressHouse() );

        }
        return elements;
    }

}
