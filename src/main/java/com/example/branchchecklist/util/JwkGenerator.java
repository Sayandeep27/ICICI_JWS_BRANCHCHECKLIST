package com.example.branchchecklist.util;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;

public class JwkGenerator {

    public static void main(String[] args) throws Exception {
        String keystorePath = "src/main/resources/keys/keystore.p12";
        String alias = "jwtkey";
        char[] password = "changeit".toCharArray();

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(keystorePath), password);

        Certificate cert = keyStore.getCertificate(alias);
        if (cert == null) {
            throw new RuntimeException("Certificate not found for alias: " + alias);
        }

        PublicKey publicKey = cert.getPublicKey();

        // Build RSA JWK
        RSAKey jwk = new RSAKey.Builder((java.security.interfaces.RSAPublicKey) publicKey)
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(Algorithm.parse("RS256"))
                .keyID(alias)
                .build();

        // Create JWKS (JSON Web Key Set)
        JWKSet jwkSet = new JWKSet(jwk);

        // Save JWKS file
        String outputPath = "src/main/resources/keys/jwks.json";
        try (FileWriter writer = new FileWriter(Paths.get(outputPath).toFile())) {
            writer.write(jwkSet.toJSONObject().toString());
        }

        System.out.println("✅ JWKS successfully generated at: " + outputPath);
        System.out.println("Public key modulus (n): " + jwk.getModulus().toString());
        System.out.println("Public exponent (e): " + jwk.getPublicExponent().toString());
    }
}
