package com.hafidsb.fitnesstracker.authservice.controller;

import com.hafidsb.fitnesstracker.common.dto.api.ApiResponse;
import com.hafidsb.fitnesstracker.common.dto.api.SuccessResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register() {
        return ResponseEntity.ok(new SuccessResponseDto<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "User registered successfully"
        ));
    }
}
