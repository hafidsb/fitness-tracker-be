package com.hafidsb.fitnesstracker.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final PrivateKey privateKey;

    public String generateAccessToken() {
        SignatureAlgorithm algorithm = Jwts.SIG.RS256;

        return Jwts.builder()
                .subject("auth-service")
                .signWith(this.privateKey, algorithm)
                .compact();
    }
}
