package com.lanwq.other;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.util.ByteSource;

import java.security.Key;

public class AESCipherUtil {

    private static final int DEFAULT_KEY_SIZE = 256;    
    private static AESCipherUtil cipherUtil = null;
    private DefaultBlockCipherService aesChipherService = null;


    public static AESCipherUtil getInstance() {
        if (cipherUtil == null) {
            cipherUtil = new AESCipherUtil();
        }
        return cipherUtil;
    }

    private AESCipherUtil() {
        aesChipherService = new AesCipherService();
        aesChipherService.setKeySize(DEFAULT_KEY_SIZE);
    }

    public Key generateKey(){
        return aesChipherService.generateNewKey(DEFAULT_KEY_SIZE);
    }    
    
    // encrypt
    public byte[] encrypt(Key key, String plaintext) {
        ByteSource encryptedBytes = aesChipherService.encrypt(CodecSupport.toBytes(plaintext), key.getEncoded());
        if (encryptedBytes != null) {
            return encryptedBytes.getBytes();
        }
        return null;
    }

    public byte[] encrypt(byte[] key, String plaintext) {
        ByteSource encryptedBytes = aesChipherService.encrypt(CodecSupport.toBytes(plaintext), key);
        if (encryptedBytes != null) {
            return encryptedBytes.getBytes();
        }
        return null;
    }

    public byte[] encrypt(Key key, byte[] plaintext) {
        ByteSource encryptedBytes = aesChipherService.encrypt(plaintext, key.getEncoded());
        if (encryptedBytes != null) {
            return encryptedBytes.getBytes();
        }
        return null;
    }

    public byte[] encrypt(byte[] key, byte[] plaintext) {
        ByteSource encryptedBytes = aesChipherService.encrypt(plaintext, key);
        if (encryptedBytes != null) {
            return encryptedBytes.getBytes();
        }
        return null;
    }

    // decrypt
    public byte[] decrypt(Key key, byte[] ciphertext) {
        ByteSource decryptedBytes = aesChipherService.decrypt(ciphertext, key.getEncoded());
        if (decryptedBytes != null) {
            return decryptedBytes.getBytes();
        }
        return null;
    }

    public byte[] decrypt(byte[] key, byte[] ciphertext) {
        ByteSource decryptedBytes = aesChipherService.decrypt(ciphertext, key);
        if (decryptedBytes != null) {
            return decryptedBytes.getBytes();
        }
        return null;
    }
     
    
    public static void main(String[] args) throws Exception{
        
        //Key key = AESCipherUtil.getInstance().generateKey();
        //System.out.println(Base64.encodeToString(key.getEncoded()));
        String originalText = "fb123";
        System.out.println("Original text: " + originalText);
        
        //byte[] byteKey = Base64.decode("Y84bTniPxW5neSCEQ7rYtlMtnEsbY+UioDC2A=");
        
        byte[] key = Base64.decode("EZq40A7GzFCuF8yUaoVVvA/qeAnHzMN/5AkkHIgR/Lg=");
        //byte[] afterEncrypt = AESCipherUtil.getInstance().encrypt(key, originalText);
        
        byte[] afterEncrypt = Base64.decode("bofu2+XTwsU5Lf6aacTw9puSy+S/igNPRjHU3nCg27A=");
        
        String afterEncryptStr = Base64.encodeToString(afterEncrypt);
        System.out.println("Encrypted String: " + afterEncryptStr);
       
        byte[] decryptedBytes = AESCipherUtil.getInstance().decrypt(key, Base64.decode(afterEncryptStr));
        System.out.println("Decrypted string: " + new String(decryptedBytes, "UTF-8") + "\n"); 
        
    }

}
