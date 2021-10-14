package handlers;


import models.AnswerTransferModels.UserAtm;
import models.DataTransferModels.LoginDto;
import models.ServerResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizationHandler {
//
//    ServerResponse<String> registerUser(RegistrationDto registrationDto) {
//        ServerResponse response = new ServerResponse<String>();
//        try {
//            LoginDto loginDto = new LoginDto();
//            loginDto.setLogin(registrationDto.getLogin());
//
//            var person = PersonContext.readFromDBPersonByLogin(loginDto);
//
//            if (person != null) {
//                response.setMessage("Имя пользователя уже занято. Придумайте другое.");
//                return response;
//            }
//
//            if (registrationDto.getPassword().length() <= 4) {
//                response.setMessage("Слишком короткий пароль. Придумайте более длинный пароль");
//                return response;
//            }
//
//            Pattern pattern = Pattern.compile("[?0-9]+");
//            Matcher matcher = pattern.matcher(registrationDto.getPassword());
//            if (!matcher.find()) {
//                response.setMessage("Пароль должен содержать хотя бы одно число");
//                return response;
//            }
//
//            String encryptedPass = PasswordUtils.generateSecurePassword(registrationDto.getPassword());
//            registrationDto.setPassword(encryptedPass);
//
//            if (registrationDto.getType() == TYPE.CLIENT.ordinal()) {
//                registrateUser(registrationDto, response, "Регистрация клиента прошла успешно");
//
//            } else if (registrationDto.getType() == TYPE.ADMIN.ordinal()) {
//                if (!registrationDto.getPascode().equals("111")) {
//                    response.setMessage("Неверный код доступа");
//                    return response;
//                }
//
//                registrateUser(registrationDto, response, "Регистрация администратора прошла успешно");
//
//            } else if (registrationDto.getType() == TYPE.EMPLOYEE.ordinal()) {
//                registrateUser(registrationDto, response, "Регистрация сотрудника прошла успешно");
//
//            } else if (registrationDto.getType() == TYPE.EXPERT.ordinal()) {
//                registrateUser(registrationDto, response, "Регистрация руководящего лица прошла успешно");
//
//            } else {
//                response.setMessage("Ошибка регистрации");
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//            response.setMessage(ex.getMessage());
//            response.setStatus(false);
//        }
//
//        return response;
//    }
//
//    private void registrateUser(RegistrationDto registrationDto, ServerResponse response, String message) {
//        try{
//            PersonContext.insertIntoDBPerson(registrationDto);
//            response.setStatus(true);
//            response.setData(message);
//        }catch (Exception ex) {
//            System.out.println(ex);
//            response.setMessage(ex.getMessage());
//            response.setStatus(false);
//        }
//    }
//
    ServerResponse<UserAtm> authorizeUser(LoginDto loginDto) {
        ServerResponse response = new ServerResponse<UserAtm>();
        try {
//            var person = PersonContext.readFromDBPersonByLogin(loginDto);
//            if (person != null) {
//
//                if (PasswordUtils.verifyUserPassword(loginDto.getPassword(), person.getPassword())) {
//                    PersonAtm personAtm = new PersonAtm();
//                    personAtm.setType(person.getType());
//                    personAtm.setFIO(person.getFIO());
//                    personAtm.setId(person.getPersonId());
//                    response.setStatus(true);
//                    response.setData(personAtm);
//                } else {
//                    response.setMessage("Не верный логин или пароль");
//                }
//            } else {
//                response.setMessage("Такой учетной записи не существует");
//            }
        } catch (Exception ex) {
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}
