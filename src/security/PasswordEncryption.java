package security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class PasswordEncryption {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] keyValue = "1234567890123456".getBytes(); // Kunci 16-byte untuk AES
    private static final byte[] ivValue = "1234567890123456".getBytes(); // IV 16-byte untuk AES

    public static String encrypt(String data) throws Exception {
        SecretKey key = new SecretKeySpec(keyValue, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivValue);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedVal = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedVal);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKey key = new SecretKeySpec(keyValue, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivValue);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedVal = cipher.doFinal(decodedValue);
        return new String(decryptedVal);
    }
}
