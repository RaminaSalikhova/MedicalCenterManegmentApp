package app.handlers.implementations;

import app.entity.User;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.SendMailDto;
import app.services.EmailService;
import app.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class SendMailHandler extends RequestHandler<SendMailDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.sendMail;
    }

    @Override
    protected String execute(SendMailDto sendMailDto) throws Exception {
        UserService userService = new UserService();
        List<User> users = userService.findAll();
        List<String> emails = new ArrayList<>();
        for (User user : users) {
            emails.add(user.getLogin());
        }
        EmailService emailService = new EmailService();
        emailService.send(sendMailDto.getText(), emails);
        return "Успешно";
    }
}
