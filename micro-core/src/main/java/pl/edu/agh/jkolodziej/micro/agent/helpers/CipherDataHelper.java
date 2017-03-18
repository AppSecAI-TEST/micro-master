package pl.edu.agh.jkolodziej.micro.agent.helpers;


import org.apache.commons.codec.ApacheBase64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author - Jakub Kołodziej
 *         helper class for encrypt and decrypt data which are exchange between micro agents
 */
public class CipherDataHelper {
    // "thisIsASecretKey";
    private static byte[] key = {0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65,
            0x79};

    /**
     * Method for encrypting data
     *
     * @param array data to encrypt in byte array form
     * @return encrypted data in String format
     * @throws Exception
     */
    public static String encryptByteArray(byte[] array) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String encryptedString = ApacheBase64.encodeBase64String(cipher.doFinal(array));
        return encryptedString;
    }

    /**
     * Method for decrypting data
     *
     * @param strToDecrypt encrypted data in String format
     * @return decrypted data in byte array format
     * @throws Exception
     */
    public static byte[] decryptByteArray(String strToDecrypt) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(ApacheBase64.decodeBase64(strToDecrypt));
    }
}
