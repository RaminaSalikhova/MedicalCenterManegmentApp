package app.helpers;

import app.entity.User;
import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.LoginDto;
import app.services.UserService;

public class LoginManager {

    public UserAtm execute(LoginDto loginDto) throws Exception {
        UserAtm personAtm = new UserAtm();

        User user = new User();
        user.setLogin("login");
        user.setPassword("P4$$w0rd");

        User person=null;

        if(user.getLogin().equals(loginDto.getLogin())) {
            person = new User();
            person.setLogin(loginDto.getLogin());
            person.setPassword(loginDto.getPassword());
        }

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
