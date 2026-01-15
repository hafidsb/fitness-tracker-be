package com.hafidsb.fitnesstracker.authservice.loader;

import com.hafidsb.fitnesstracker.authservice.properties.JwtKeyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class PrivateKeyLoader {

    private final JwtKeyProperties properties;

    public PrivateKey load() {
        try {
            byte[] keyBytes = Files.readAllBytes(Path.of(properties.privateKeyPath()));

            String pem = new String(keyBytes)
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(pem);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePrivate(keySpec);

        } catch (Exception e) {
            throw new IllegalStateException("Failed to load JWT private key", e);
        }
    }
}
