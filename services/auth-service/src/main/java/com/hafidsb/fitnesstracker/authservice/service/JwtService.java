package com.hafidsb.fitnesstracker.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final PrivateKey privateKey;

    @Value("${security.jwt.expiration}")
    private Long expirationInSeconds;

    public String generateAccessToken(String sub) {
        SignatureAlgorithm algorithm = Jwts.SIG.RS256;

        return Jwts.builder()
                .subject(sub)
                .issuer("http://auth-service")
                .claim("scope", "internal")
                .signWith(this.privateKey, algorithm)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationInSeconds * 1000))
                .compact();
    }

    public String generateInternalToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .claim("scope", "internal")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }
}
