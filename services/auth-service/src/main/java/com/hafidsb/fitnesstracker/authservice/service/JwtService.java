package com.hafidsb.fitnesstracker.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
public class JwtService {

    public String generateAccessToken() {
        SignatureAlgorithm algorithm = Jwts.SIG.RS256;
        KeyPair pair = algorithm.keyPair().build();

        return Jwts.builder()

                .compact();
    }
}
