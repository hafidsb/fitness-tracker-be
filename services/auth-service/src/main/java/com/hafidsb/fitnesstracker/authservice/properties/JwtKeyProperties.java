package com.hafidsb.fitnesstracker.authservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public record JwtKeyProperties(
        String privateKeyPath
) {}
