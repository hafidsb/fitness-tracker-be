package com.hafidsb.fitnesstracker.authservice.testconfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@TestConfiguration
public class JwtTestConfig {

    @Bean
    public PrivateKey testPrivateKey() throws Exception {
        InputStream is = getClass().getResourceAsStream("/keys/test_private_key.pem");

        byte[] keyBytes = is.readAllBytes();

        String pem = new String(keyBytes)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(pem);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
}
