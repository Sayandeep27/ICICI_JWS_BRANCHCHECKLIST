package com.example.branchchecklist.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class AESUtil {

    @Value("${app.aes.key}")
    private String base64Key;

    private byte[] key;

    private static final int IV_SIZE = 12; // GCM nonce
    private static final int TAG_LENGTH_BIT = 128;

    @PostConstruct
    public void init() {
        key = Base64.getDecoder().decode(base64Key);
    }

    public String encrypt(String plain) throws Exception {
        if (plain == null) plain = "";
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);
        byte[] encrypted = cipher.doFinal(plain.getBytes("UTF-8"));

        byte[] out = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, out, 0, iv.length);
        System.arraycopy(encrypted, 0, out, iv.length, encrypted.length);
        return Base64.getEncoder().encodeToString(out);
    }

    public String decrypt(String cipherText) throws Exception {
        if (cipherText == null || cipherText.isEmpty()) return "";
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(decoded, 0, iv, 0, IV_SIZE);
        byte[] encrypted = new byte[decoded.length - IV_SIZE];
        System.arraycopy(decoded, IV_SIZE, encrypted, 0, encrypted.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted, "UTF-8");
    }
}
