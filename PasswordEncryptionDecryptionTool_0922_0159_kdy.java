// 代码生成时间: 2025-09-22 01:59:02
package com.security;

import javax.crypto.Cipher;
# NOTE: 重要实现细节
import javax.crypto.KeyGenerator;
# 增强安全性
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryptionDecryptionTool {

    // Default encryption and decryption algorithm
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY_BYTE_SIZE = new byte[16]; // AES key size of 128-bits
    private static final String PROVIDER = "SunJCE";

    // Generate a new AES key for encryption and decryption
# 增强安全性
    public static SecretKey generateAESKey() throws Exception {
# TODO: 优化性能
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM, PROVIDER);
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    // Encrypt a plain text password
    public static String encryptPassword(String password, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt an encrypted password
    public static String decryptPassword(String encryptedPassword, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    // Main method for demonstration purposes
    public static void main(String[] args) {
        try {
            // Generate a new AES key
            SecretKey key = generateAESKey();

            // Define a plain password
            String plainPassword = "MySecurePassword123";

            // Encrypt the password
            String encryptedPassword = encryptPassword(plainPassword, key);
            System.out.println("Encrypted Password: " + encryptedPassword);

            // Decrypt the password
            String decryptedPassword = decryptPassword(encryptedPassword, key);
            System.out.println("Decrypted Password: " + decryptedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
