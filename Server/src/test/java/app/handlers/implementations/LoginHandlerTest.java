package app.handlers.implementations;

import app.entity.User;
import app.helpers.LoginManager;
import app.models.DataTransferModels.LoginDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginHandlerTest {
    private LoginManager userManager;

    @Test
    public void testUserLoginWithEmptyPassword() throws Exception {
        userManager = new LoginManager();
        LoginDto loginDto = new LoginDto();
        loginDto.setLogin("login");
        loginDto.setPassword("P4$$w0rd");

        Exception exception = assertThrows(Exception.class, () -> {
            userManager.execute(loginDto);
        });

        String expectedMessage = "Не верный логин или пароль";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));//Проверяет, что логическое условие истинно.
    }


    @Test
    public void testUserLoginWithIncorrectLogin() throws Exception {
        userManager = new LoginManager();
        LoginDto loginDto = new LoginDto();
        loginDto.setLogin("login1");
        loginDto.setPassword("P4$$w0rd");

        User user = new User();
        user.setLogin("login");
        user.setPassword("P4$$w0rd");

        Exception exception = assertThrows(Exception.class, () -> {
            userManager.execute(loginDto);
        });

        String expectedMessage = "Такой учетной записи не существует";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUserLoginWithIncorrectPassword() throws Exception {
        userManager = new LoginManager();
        LoginDto loginDto = new LoginDto();
        loginDto.setLogin("login");
        loginDto.setPassword("password");

        User user = new User();
        user.setLogin("login");
        user.setPassword("P4$$w0rd");

        Exception exception = assertThrows(Exception.class, () -> {
            userManager.execute(loginDto);
        });

        String expectedMessage = "Не верный логин или пароль";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}