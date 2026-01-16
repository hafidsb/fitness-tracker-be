package com.hafidsb.fitnesstracker.authservice.config;

import com.hafidsb.fitnesstracker.authservice.properties.JwtKeyProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Configuration
@EnableConfigurationProperties(JwtKeyProperties.class)
public class JwtConfig {

    @Bean
    public PrivateKey jwtPrivateKey(
            @Value("${JWT_PRIVATE_KEY_PATH}") Path keyPath
    ) {
        try {
            String pem = Files.readString(keyPath)
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(pem);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);

            return KeyFactory
                    .getInstance("RSA")
                    .generatePrivate(keySpec);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "JWT private key not found. Run generate-jwt-key.sh",
                    e
            );
        }
    }
}
