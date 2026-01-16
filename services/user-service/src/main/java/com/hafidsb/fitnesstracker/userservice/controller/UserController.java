package com.hafidsb.fitnesstracker.userservice.controller;

import com.hafidsb.fitnesstracker.common.dto.UserSummaryDto;
import com.hafidsb.fitnesstracker.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserSummaryDto me(@AuthenticationPrincipal Jwt jwt) {
        return userService.getUserFromAuth(jwt.getSubject());
    }
}
