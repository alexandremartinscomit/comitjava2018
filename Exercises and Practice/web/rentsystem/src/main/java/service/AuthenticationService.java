package service;

import exception.InfrastructureException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class AuthenticationService {

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final String SALT = "KLIkLIhCVy";

    public AuthenticationService(){

    }

    public byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new InfrastructureException("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
    public String generateSecurePassword(String password) {
        byte[] securePassword = hash(password.toCharArray(), SALT.getBytes());
        return Base64.getEncoder().encodeToString(securePassword);
    }

    public boolean verifyUserPassword(String providedPassword,
                                             String securedPassword)
    {
        // Generate New secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword);

        // Check if two passwords are equal
        return newSecurePassword.equalsIgnoreCase(securedPassword);
    }
}
