package hbv.example.passwordhash;


import java.util.*;
import static java.lang.System.out;
import java.security.SecureRandom;
 import java.security.NoSuchAlgorithmException;
 import java.security.spec.InvalidKeySpecException;
 import javax.crypto.SecretKey;
 import javax.crypto.SecretKeyFactory;
 import javax.crypto.spec.PBEKeySpec;

    public class hashingpassword {

        public static void main(String[] args) throws Exception {
            doHash();
        }

        public static void doHash() throws Exception {
            String password = "password"; // the password to be hashed
            byte[] salt = generateSalt(); // Generate salt
            byte[] hash = hashPassword(password, salt); // Hash the password with salt

            // Print the salt and hash in hexadecimal format

        }

        // Generates a random 8-byte salt
        public static byte[] generateSalt() throws NoSuchAlgorithmException {
            SecureRandom random = SecureRandom.getInstanceStrong();
            byte[] salt = new byte[8]; // 8-byte salt as recommended by RSA PKCS5
            random.nextBytes(salt);
            return salt;
        }

        // Hash the password using PBKDF2 with HMAC SHA-512
        public static byte[] hashPassword(String password, byte[] salt)
                throws NoSuchAlgorithmException, InvalidKeySpecException {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 210000, 512); // 210000 iterations, 512 bits output
            SecretKey key = secretKeyFactory.generateSecret(spec);
            spec.clearPassword();
            return key.getEncoded();
        }
    }




