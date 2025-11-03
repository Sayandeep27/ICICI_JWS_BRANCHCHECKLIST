package com.example.branchchecklist.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Date;

@Service
public class JwtService {

    private static final String KEYSTORE_PATH = "keys/keystore.p12";
    private static final String KEYSTORE_PASSWORD = "changeit";
    private static final String KEY_ALIAS = "jwtkey";
    private static final String KEY_PASSWORD = "changeit";
    private static final long EXPIRATION_TIME_MS = 3600_000; // 1 hour

    private final KeyPair keyPair;

    public JwtService() {
        this.keyPair = loadKeys();
    }

    private KeyPair loadKeys() {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(KEYSTORE_PATH)) {
                if (is == null) {
                    throw new RuntimeException("Keystore not found at path: " + KEYSTORE_PATH);
                }
                keyStore.load(is, KEYSTORE_PASSWORD.toCharArray());
            }

            Key key = keyStore.getKey(KEY_ALIAS, KEY_PASSWORD.toCharArray());
            if (!(key instanceof PrivateKey privateKey)) {
                throw new IllegalStateException("Key is not a private key");
            }

            Certificate cert = keyStore.getCertificate(KEY_ALIAS);
            PublicKey publicKey = cert.getPublicKey();

            return new KeyPair(publicKey, privateKey);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load keys from keystore", e);
        }
    }

    // Generate JWT token
    public String generateToken(String mobile) {
        return Jwts.builder()
                .subject(mobile)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    // Extract mobile number (subject) from token
    public String extractMobile(String token) {
        return Jwts.parser()
                .verifyWith(keyPair.getPublic())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Validate token
    public boolean validateToken(String token, String mobile) {
        try {
            String extracted = extractMobile(token);
            return extracted.equals(mobile) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Check if token expired
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(keyPair.getPublic())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }
}
