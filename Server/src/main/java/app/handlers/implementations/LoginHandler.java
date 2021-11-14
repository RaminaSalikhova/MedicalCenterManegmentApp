package app.handlers.implementations;

import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.helpers.PasswordUtils;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.LoginDto;
import app.services.UserService;

public class LoginHandler extends RequestHandler<LoginDto, UserAtm> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.login;
    }

    @Override
    protected UserAtm execute(LoginDto loginDto) throws Exception {
        UserAtm personAtm = new UserAtm();

        UserService userService = new UserService();
        var person = userService.findByLogin(loginDto.getLogin());
        if (person != null) {
            if (PasswordUtils.verifyUserPassword(loginDto.getPassword(), person.getPassword())) {
                personAtm.setRole(person.getRole());
                personAtm.setFirstName(person.getFirstName());
                personAtm.setId(person.getId());
            } else {
                throw new Exception("Не верный логин или пароль");
            }
        } else {
            throw new Exception("Такой учетной записи не существует");
        }

        return personAtm;
    }
}