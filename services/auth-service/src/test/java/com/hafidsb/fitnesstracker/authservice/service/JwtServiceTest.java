package com.hafidsb.fitnesstracker.authservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtServiceTest {

    @Test
    void testGenerateAccessToken() {
        JwtService jwtService = new JwtService();
        String accessToken = jwtService.generateAccessToken();

        assertEquals("token", accessToken);
    }
}
