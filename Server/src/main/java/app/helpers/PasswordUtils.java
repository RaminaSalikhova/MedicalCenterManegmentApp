package app.helpers;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class PasswordUtils {
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


    public static String generateSecurePassword(String password) {
        String returnValue = null;

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        returnValue = Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }

    public static boolean verifyUserPassword(String providedPassword, String securedPassword)
    {
        boolean returnValue = false;

        String newSecurePassword = generateSecurePassword(providedPassword);

        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

        return returnValue;
    }
}
