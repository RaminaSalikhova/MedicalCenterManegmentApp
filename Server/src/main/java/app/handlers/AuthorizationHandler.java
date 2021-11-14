//package app.handlers;
//
//
//import app.entity.Doctor;
//import app.entity.Patient;
//import app.entity.User;
//import app.enums.TYPE;
//import app.helpers.PasswordUtils;
//import app.models.AnswerTransferModels.UserAtm;
//import app.models.DataTransferModels.LoginDto;
//import app.models.DataTransferModels.RegistrationDto;
//import app.connection.ServerResponse;
//import app.services.DoctorService;
//import app.services.PatientService;
//import app.services.UserService;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class AuthorizationHandler {
//
//    ServerResponse<String> registerUser(RegistrationDto registrationDto) {
//        ServerResponse response = new ServerResponse<String>();
//        try {
////            LoginDto loginDto = new LoginDto();
////            loginDto.setLogin(registrationDto.getLogin());
//            UserService userService = new UserService();
//
//            var person = userService.findByLogin(registrationDto.getLogin());
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
//            if (registrationDto.getRole().equals(TYPE.PATIENT.toString())) {
//                registrateUser(registrationDto, response, "Регистрация клиента прошла успешно");
//
//            } else if (registrationDto.getRole().equals(TYPE.ADMIN.toString())) {
//                registrateUser(registrationDto, response, "Регистрация администратора прошла успешно");
//
//            } else if (registrationDto.getRole().equals(TYPE.DOCTOR.toString())) {
//                registrateUser(registrationDto, response, "Регистрация сотрудника прошла успешно");
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
//        try {
//            User newUser = new User();
//            newUser.setPassword(registrationDto.getPassword());
//            newUser.setLogin(registrationDto.getLogin());
//            newUser.setFirstName(registrationDto.getFirstName());
//            newUser.setLastName(registrationDto.getLastName());
//            newUser.setPatronymic(registrationDto.getPatronymic());
//            newUser.setRole(registrationDto.getRole());
//
//            newUser.setDateOfBirth(registrationDto.getDateOfBirth());
//            newUser.setPhoneNum(registrationDto.getPhoneNum());
//            newUser.setStatus(true);
//
//            UserService userService = new UserService();
//            userService.save(newUser);
//
//            if (registrationDto.getRole().equals(TYPE.PATIENT.toString())) {
//                Patient newPatient=new Patient();
//                User user= userService.findByLogin(newUser.getLogin());
//                newPatient.setUserByUserId(user);
//
//                PatientService patientService=new PatientService();
//                patientService.save(newPatient);
//
//            }
//            else if (registrationDto.getRole().equals(TYPE.DOCTOR.toString())) {
//                Doctor newDoctor=new Doctor();
//                User user= userService.findByLogin(newUser.getLogin());
//                newDoctor.setUserByUserId(user);
//
//                DoctorService doctorService=new DoctorService();
//                doctorService.save(newDoctor);
//            }
//            response.setStatus(true);
//            response.setData(message);
//        } catch (Exception ex) {
//            System.out.println(ex);
//            response.setMessage(ex.getMessage());
//            response.setStatus(false);
//        }
//    }
//
//    ServerResponse<UserAtm> authorizeUser(LoginDto loginDto) {
//        ServerResponse response = new ServerResponse<UserAtm>();
//        try {
//            UserService userService = new UserService();
//            var person = userService.findByLogin(loginDto.getLogin());
//            if (person != null) {
//
//                if (PasswordUtils.verifyUserPassword(loginDto.getPassword(), person.getPassword())) {
//                    UserAtm personAtm = new UserAtm();
//                    personAtm.setRole(person.getRole());
//                    personAtm.setFirstName(person.getFirstName());
//                    personAtm.setId(person.getId());
//                    response.setStatus(true);
//                    response.setData(personAtm);
//                } else {
//                    response.setMessage("Не верный логин или пароль");
//                }
//            } else {
//                response.setMessage("Такой учетной записи не существует");
//            }
//        } catch (Exception ex) {
//            response.setMessage(ex.getMessage());
//        }
//        return response;
//    }
//}
