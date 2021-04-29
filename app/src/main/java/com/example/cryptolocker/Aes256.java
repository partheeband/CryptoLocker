package com.example.cryptolocker;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.ArrayList;

public class Aes256 {

    //private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
    private static final String SALT = "mpQnAhyeeaPFijbG1tkJzTukvtUVfq1W";


    public static String encrypt(String strToEncrypt,String SECRET_KEY) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

            return Base64.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)), Base64.DEFAULT);


            //return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    public static String decrypt(String strToDecrypt,String SECRET_KEY) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.decode(strToDecrypt,Base64.DEFAULT)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static ArrayList<String> encrypt(String strToEncrypt1,String strToEncrypt2,String strToEncrypt3,String strToEncrypt4,String SECRET_KEY) {
        try {

            ArrayList<String> encryptionData = new ArrayList<>();
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

           // return Base64.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)), Base64.DEFAULT);
            encryptionData.add(Base64.encodeToString(cipher.doFinal(strToEncrypt1.getBytes(StandardCharsets.UTF_8)), Base64.DEFAULT));
            encryptionData.add(Base64.encodeToString(cipher.doFinal(strToEncrypt2.getBytes(StandardCharsets.UTF_8)), Base64.DEFAULT));
            encryptionData.add(Base64.encodeToString(cipher.doFinal(strToEncrypt3.getBytes(StandardCharsets.UTF_8)), Base64.DEFAULT));
            encryptionData.add(Base64.encodeToString(cipher.doFinal(strToEncrypt4.getBytes(StandardCharsets.UTF_8)), Base64.DEFAULT));
            return encryptionData;

        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    public static ArrayList<String> decrypt(String strToDecrypt1,String strToDecrypt2,String strToDecrypt3,String strToDecrypt4,String SECRET_KEY) {
        try {

            ArrayList<String> decryptionData = new ArrayList<>();
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

//            return new String(cipher.doFinal(Base64.decode(strToDecrypt1,Base64.DEFAULT)));

            decryptionData.add(new String(cipher.doFinal(Base64.decode(strToDecrypt1,Base64.DEFAULT))));
            decryptionData.add(new String(cipher.doFinal(Base64.decode(strToDecrypt2,Base64.DEFAULT))));
            decryptionData.add(new String(cipher.doFinal(Base64.decode(strToDecrypt3,Base64.DEFAULT))));
            decryptionData.add(new String(cipher.doFinal(Base64.decode(strToDecrypt4,Base64.DEFAULT))));

            return decryptionData;
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
