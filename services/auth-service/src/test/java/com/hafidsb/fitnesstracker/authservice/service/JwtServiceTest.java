package com.hafidsb.fitnesstracker.authservice.service;

import com.hafidsb.fitnesstracker.authservice.testconfig.JwtTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JwtService.class,
        JwtTestConfig.class
})
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;


    @Test
    void shouldGenerateValidJwt() {
        String accessToken = jwtService.generateAccessToken();

        assertNotNull(accessToken);
        assertEquals(3, accessToken.split("\\.").length);
    }
}
