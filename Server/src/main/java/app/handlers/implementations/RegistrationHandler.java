package app.handlers.implementations;

import app.connection.ServerResponse;
import app.entity.Doctor;
import app.entity.Patient;
import app.entity.Schedule;
import app.entity.User;
import app.enums.HANDLER_TYPE;
import app.enums.TYPE;
import app.handlers.RequestHandler;
import app.helpers.PasswordUtils;
import app.models.DataTransferModels.RegistrationDto;
import app.services.DoctorService;
import app.services.PatientService;
import app.services.ScheduleService;
import app.services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationHandler extends RequestHandler<RegistrationDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.register;
    }

    @Override
    protected String execute(RegistrationDto registrationDto) throws Exception {

        String successMessage = "";
        UserService userService = new UserService();

        var person = userService.findByLogin(registrationDto.getLogin());

        if (person != null) {
            throw new Exception("Имя пользователя уже занято. Придумайте другое.");
        }

        if (registrationDto.getPassword().length() <= 4) {
            throw new Exception("Слишком короткий пароль. Придумайте более длинный пароль");
        }

        Pattern pattern = Pattern.compile("[?0-9]+");
        Matcher matcher = pattern.matcher(registrationDto.getPassword());
        if (!matcher.find()) {
            throw new Exception("Пароль должен содержать хотя бы одно число");
        }

        String encryptedPass = PasswordUtils.generateSecurePassword(registrationDto.getPassword());
        registrationDto.setPassword(encryptedPass);

        if (registrationDto.getRole().equals(TYPE.PATIENT.toString())) {
            registrateUser(registrationDto);
            successMessage = "Регистрация клиента прошла успешно";
        } else if (registrationDto.getRole().equals(TYPE.ADMIN.toString())) {
            registrateUser(registrationDto);
            successMessage = "Регистрация администратора прошла успешно";
        } else if (registrationDto.getRole().equals(TYPE.DOCTOR.toString())) {
            registrateUser(registrationDto);
            successMessage = "Регистрация сотрудника прошла успешно";
        } else {
            throw new Exception("Ошибка регистрации");
        }

        return successMessage;
    }

    private void registrateUser(RegistrationDto registrationDto) {

        User newUser = mapNewUser(registrationDto);

        UserService userService = new UserService();
        userService.save(newUser);

        User user = userService.findByLogin(newUser.getLogin());
        if (registrationDto.getRole().equals(TYPE.PATIENT.toString())) {
            Patient newPatient = new Patient();
            newPatient.setUserByUserId(user);

            PatientService patientService = new PatientService();
            patientService.save(newPatient);

        } else if (registrationDto.getRole().equals(TYPE.DOCTOR.toString())) {
            Doctor newDoctor = new Doctor();
            newDoctor.setUserByUserId(user);

            DoctorService doctorService = new DoctorService();
            doctorService.save(newDoctor);
        }
    }

    private User mapNewUser(RegistrationDto registrationDto)
    {
        User newUser = new User();

        newUser.setPassword(registrationDto.getPassword());
        newUser.setLogin(registrationDto.getLogin());
        newUser.setFirstName(registrationDto.getFirstName());
        newUser.setLastName(registrationDto.getLastName());
        newUser.setPatronymic(registrationDto.getPatronymic());
        newUser.setRole(registrationDto.getRole());
        newUser.setDateOfBirth(registrationDto.getDateOfBirth());
        newUser.setPhoneNum(registrationDto.getPhoneNum());
        newUser.setStatus(true);

        return newUser;
    }
}