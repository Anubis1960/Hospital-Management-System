package Utilities;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class is responsible for encrypting the password.
 */
public class Encrypt {

    /**
     * This method is used to encrypt the password.
     * It uses the SHA-256 algorithm to encrypt the password.
     * @param password
     * @return byte[]
     */
    public static byte[] encrypt(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(password.getBytes(StandardCharsets.UTF_8));
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * This method is used to convert the byte array to hexadecimal string.
     * @param hash
     * @return String
     */
    public static String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}
