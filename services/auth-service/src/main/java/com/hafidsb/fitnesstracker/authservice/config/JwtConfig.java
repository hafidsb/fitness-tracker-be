package com.hafidsb.fitnesstracker.authservice.config;

import com.hafidsb.fitnesstracker.authservice.loader.PrivateKeyLoader;
import com.hafidsb.fitnesstracker.authservice.properties.JwtKeyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;

@Configuration
@EnableConfigurationProperties(JwtKeyProperties.class)
public class JwtConfig {

    @Bean
    public PrivateKey jwtPrivateKey(PrivateKeyLoader loader) {
        return loader.load();
    }
}
