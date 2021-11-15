package app.controllers;

import app.connection.ClientConnection;
import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;
import app.models.AnswerTransferModels.*;
import app.models.DataTransferModels.*;
import app.models.holder.UserHolder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
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

public class AdminHomeController implements Initializable {

    @FXML
    TableView<GetUserListAtm> tableViewUsers;


    @FXML
    private TableColumn<GetUserListAtm, String> idColumnIDUser, idColumnFirstName, idColumnLastName, idColumnPatronymic, idColumnLogin, idColumnPhoneNum, idColumnRole, idColumnIDStatus;


    @FXML
    TableView<GetCommentListAtm> tableViewComments;

    @FXML
    private TableColumn<GetCommentListAtm, String> idColumnIDComment, idColumnMessage;

    @FXML
    private JFXButton backBtn, sendBtn, doctorBtn;

    @FXML
    private BarChart<String, Number> chart;

    @FXML
    private JFXTextArea textarea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chart.setTitle("КОличество записей к каждому врачу");
        var data = new XYChart.Series<String, Number>();

        GetDoctorsAndAppointmentCountDto getAppointmentCountDto=new GetDoctorsAndAppointmentCountDto();

        ClientRequest<GetDoctorsAndAppointmentCountDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getDoctorsAndAppointmentCountDto);
        request.setData(getAppointmentCountDto);

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
        List<GetDoctorsAndAppointmentCountAtm> getDoctorsAndAppointmentCountAtmList= (List<GetDoctorsAndAppointmentCountAtm>) response.getData();

        for(GetDoctorsAndAppointmentCountAtm element: getDoctorsAndAppointmentCountAtmList) {
            data.getData().add(new XYChart.Data<>(element.getDoctorName(), element.getAppointmentsCount()));
        }
        chart.getData().add(data);

        idColumnIDUser.setCellValueFactory(new PropertyValueFactory<GetUserListAtm, String>("userID"));
        idColumnFirstName.setCellValueFactory(new PropertyValueFactory<GetUserListAtm, String>("first_name"));
        idColumnLastName.setCellValueFactory(new PropertyValueFactory<GetUserListAtm, String>("last_name"));
        idColumnPatronymic.setCellValueFactory(new PropertyValueFactory<GetUserListAtm, String>("patronymic"));
        idColumnLogin.setCellValueFactory(new PropertyValueFactory<GetUserListAtm, String>("login"));
        idColumnPhoneNum.setCellValueFactory(new PropertyValueFactory<GetUserListAtm, String>("phoneNum"));
        idColumnRole.setCellValueFactory(new PropertyValueFactory<GetUserListAtm, String>("role"));
        idColumnIDStatus.setCellValueFactory(new PropertyValueFactory<GetUserListAtm, String>("status"));

        tableViewUsers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableViewUsers.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = tableViewUsers.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    int row= tablePosition.getRow();
                    GetUserListAtm getDoctorListAtm=tableViewUsers.getItems().get(row);

                    System.out.println("Selected Value" + val);
                    deleteUser(getDoctorListAtm.getUserID());
                    try {
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminHome.fxml"));
//                        loader.setLocation(getClass().getResource("availableTime.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root, 1024, 640);
                        stage.setScene(scene);
                        stage.show();
                        stage.setResizable(false);

                        Stage newStage = (Stage) tableViewUsers.getScene().getWindow();
                        newStage.hide();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        tableViewUsers.getItems().setAll(getUsersToAdd());

        idColumnIDComment.setCellValueFactory(new PropertyValueFactory<GetCommentListAtm, String>("commentID"));
        idColumnMessage.setCellValueFactory(new PropertyValueFactory<GetCommentListAtm, String>("message"));

        tableViewComments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableViewComments.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = tableViewComments.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    int row= tablePosition.getRow();
                    GetCommentListAtm getCommentListAtm=tableViewComments.getItems().get(row);

                    System.out.println("Selected Value" + val);
                    deleteComment(getCommentListAtm.getCommentID());
                    try {
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminHome.fxml"));
//                        loader.setLocation(getClass().getResource("availableTime.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root, 1024, 640);
                        stage.setScene(scene);
                        stage.show();
                        stage.setResizable(false);

                        Stage newStage = (Stage) tableViewComments.getScene().getWindow();
                        newStage.hide();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        tableViewComments.getItems().setAll(getCommentsToAdd());

    }

    private void deleteUser(long id){
        DeleteUserDto deleteUserDto=new DeleteUserDto();
        deleteUserDto.setUserID(id);
        ClientRequest<DeleteUserDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.deleteUser);
        request.setData(deleteUserDto);
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

    private void deleteComment(long id){
        DeleteCommentDto deleteCommentDto=new DeleteCommentDto();
        deleteCommentDto.setCommentID(id);
        ClientRequest<DeleteCommentDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.deleteComment);
        request.setData(deleteCommentDto);
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

    private List<GetUserListAtm> getUsersToAdd() {
        GetUserListDto getUserListDto = new GetUserListDto();

        ClientRequest<GetUserListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getUserList);
        request.setData(getUserListDto);

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
        return (List<GetUserListAtm>) response.getData();
    }

    private List<GetCommentListAtm> getCommentsToAdd() {
        GetCommentListDto getCommentListDto = new GetCommentListDto();

        ClientRequest<GetCommentListDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.getCommentList);
        request.setData(getCommentListDto);

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
        return (List<GetCommentListAtm>) response.getData();
    }

    private void sendMail(){
        SendMailDto sendMailDto = new SendMailDto();

        ClientRequest<SendMailDto> request = new ClientRequest<>();
        request.setType(HANDLER_TYPE.sendMail);
        sendMailDto.setText(textarea.getText());
        request.setData(sendMailDto);

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
        } else if(ae.getSource() == sendBtn){
            sendMail();
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
        }else if(ae.getSource() == doctorBtn){
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
