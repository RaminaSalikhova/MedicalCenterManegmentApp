package app.handlers.implementations;

import app.connection.ServerResponse;
import app.entity.User;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.LoginDto;
import app.services.UserService;

public class GetUserDataHandler extends RequestHandler<LoginDto, UserAtm> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getUserData;
    }

    @Override
    protected UserAtm execute(LoginDto loginDto) throws Exception {

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

        return userAtm;
    }
}
