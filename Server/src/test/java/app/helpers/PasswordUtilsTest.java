package app.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {


    @Test
    void verifyUserPassword() {
        String providedPassword="ramina123";
        String securedPassword="dJuQKKdsH5zptyMiC3tFJM8L3Bw4MRbnp6TtoCctjJw=";
        boolean expected=true;
        boolean actual=PasswordUtils.verifyUserPassword(providedPassword, securedPassword);
        Assertions.assertEquals(expected, actual);//Проверяет, что два значения совпадают. Примечание: длямассивов проверяются ссылки, а не содержание массивов.
    }

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final String salt = "EqdmPh53c9x33EygXpTpcoJvc4VXLK";

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    @Test
     void generateSecurePassword() {
        String password="ramina123";
        String expected="dJuQKKdsH5zptyMiC3tFJM8L3Bw4MRbnp6TtoCctjJw=";


        String actual = null;

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        actual = Base64.getEncoder().encodeToString(securePassword);

        Assertions.assertEquals(expected, actual);
    }
}