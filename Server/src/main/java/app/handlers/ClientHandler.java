package app.handlers;

import app.connection.ClientRequest;
import app.entity.User;
import app.helpers.ObjectMessenger;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.LoginDto;
import app.models.DataTransferModels.RegistrationDto;
import app.connection.ServerResponse;
import app.models.DataTransferModels.UpdateUserByPatientDto;
import app.services.UserService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    protected final Socket s;
    DataInputStream dis;
    DataOutputStream dout;

    public ClientHandler(Socket s, DataInputStream dis,
                         DataOutputStream dout) {
        this.s = s;
        this.dis = dis;
        this.dout = dout;
    }

    public void run() {
        System.out.println("Server started");
        try {
            ObjectMessenger om = new ObjectMessenger(s);
            while (true) {

                ClientRequest req = (ClientRequest) om.receiveObject();

////                ServerResponse response = new ServerResponse<>();
////
////                String registrationDto;
////                registrationDto = (String) req.getData();
////                System.out.println(registrationDto);
////
////                response.setData("okay");
////
////                om.sendObject(response);

                switch (req.getType()) {
                    case login: {
                        ServerResponse response;

                        var loginDto = (LoginDto) req.getData();
                        AuthorizationHandler authorization = new AuthorizationHandler();
                        response = authorization.authorizeUser(loginDto);

                        om.sendObject(response);
                        break;
                    }
                    case register: {
                        ServerResponse response;

                        RegistrationDto registrationDto;
                        registrationDto = (RegistrationDto) req.getData();
                        AuthorizationHandler authorization = new AuthorizationHandler();
                        response = authorization.registerUser(registrationDto);

                        om.sendObject(response);
                        break;
                    }
                    case getUserData: {
                        ServerResponse response = new ServerResponse<UserAtm>();

                        var loginDto = (LoginDto) req.getData();
                        UserService userService = new UserService();
                        User user = userService.findByLogin(loginDto.getLogin());
                        UserAtm userAtm = new UserAtm();
                        userAtm.setId(user.getId());
                        userAtm.setRole(user.getRole());
                        userAtm.setFirstName(user.getFirstName());
                        userAtm.setLogin(user.getLogin());
                        userAtm.setDateOfBirth(user.getDateOfBirth());
                        userAtm.setPatronymic(user.getPatronymic());
                        userAtm.setPhoneNum(user.getPhoneNum());
                        userAtm.setLastName(user.getLastName());
                        response.setStatus(true);
                        response.setData(userAtm);


                        om.sendObject(response);
                        break;
                    }
                    case updateUserByPatient:{
                        ServerResponse response=new ServerResponse<String>();

                        UpdateUserByPatientDto updateUserByPatientDto;
                        updateUserByPatientDto = (UpdateUserByPatientDto) req.getData();
                        UserService userService=new UserService();
                        userService.updateUsernameAndPhone(updateUserByPatientDto);
                        response.setStatus(true);

                        om.sendObject(response);
                        break;
                    }
                    case close: {
                        s.close();
                        dis.close();
                        dout.close();
                        return;
                    }
                }
            }
        } catch (
                Exception e) {
            System.out.println(e);
        }
    }
}