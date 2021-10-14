package handlers;

import helpers.ObjectMessenger;
import models.ClientRequest;
import models.DataTransferModels.LoginDto;
import models.ServerResponse;

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

//                ServerResponse response = new ServerResponse<>();
//
//                String registrationDto;
//                registrationDto = (String) req.getData();
//                System.out.println(registrationDto);
//
//                response.setData("okay");
//
//                om.sendObject(response);

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
//                        ServerResponse response;
//
//                        RegistrationDto registrationDto;
//                        registrationDto = (RegistrationDto) req.getData();
//                        AuthorizationHandler authorization = new AuthorizationHandler();
//                        response = authorization.registerUser(registrationDto);
//
//                        om.sendObject(response);
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