package io.data.encryption.utils;

import io.data.encryption.config.PropertiesConfiguration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtils {


    private static SecretKeySpec sec;
    private static IvParameterSpec iv;

    static {
        sec = new SecretKeySpec(PropertiesConfiguration.SECRET_KEY.getBytes(), PropertiesConfiguration.SECRET_KEY_ALGORITHM);
        iv = new IvParameterSpec(PropertiesConfiguration.IV_KEY.getBytes());
    }


    public static String encrypt(String value) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(PropertiesConfiguration.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, sec, iv);
        byte[] cipherText = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(PropertiesConfiguration.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, sec, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(plainText);
    }
}
