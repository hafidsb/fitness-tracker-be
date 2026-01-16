package com.hafidsb.fitnesstracker.authservice.controller;

import com.hafidsb.fitnesstracker.authservice.dto.LoginRequestDto;
import com.hafidsb.fitnesstracker.authservice.dto.LoginResponseDto;
import com.hafidsb.fitnesstracker.authservice.dto.RegisterUserRequestDto;
import com.hafidsb.fitnesstracker.authservice.dto.RegisterUserResponseDto;
import com.hafidsb.fitnesstracker.authservice.entity.User;
import com.hafidsb.fitnesstracker.authservice.service.JwtService;
import com.hafidsb.fitnesstracker.authservice.service.UserService;
import com.hafidsb.fitnesstracker.common.dto.api.ApiResponse;
import com.hafidsb.fitnesstracker.common.dto.api.SuccessResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid RegisterUserRequestDto dto) {
        User user = userService.registerAndReturn(dto);
        String token = jwtService.generateAccessToken(user.getId().toString());

        return ResponseEntity.ok(new SuccessResponseDto<>(
                HttpStatus.OK.value(),
                "User registered successfully",
                new RegisterUserResponseDto(token)
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginRequestDto dto) {
        User user = userService.loginAndReturn(dto);
        String token = jwtService.generateAccessToken(user.getId().toString());

        return ResponseEntity.ok(new SuccessResponseDto<>(
                HttpStatus.OK.value(),
                "Success",
                new LoginResponseDto(
                        user.getId(),
                        user.getEmail(),
                        token
                )
        ));
    }
}
