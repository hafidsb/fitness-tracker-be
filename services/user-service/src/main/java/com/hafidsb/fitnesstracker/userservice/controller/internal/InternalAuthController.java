package com.hafidsb.fitnesstracker.userservice.controller.internal;

import com.hafidsb.fitnesstracker.common.dto.api.ApiResponse;
import com.hafidsb.fitnesstracker.common.dto.api.SuccessResponseDto;
import com.hafidsb.fitnesstracker.userservice.service.InternalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/auth")
@RequiredArgsConstructor
public class InternalAuthController {

    private final InternalUserService internalUserService;

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> registerProfile(@PathVariable("id") String id) {
        internalUserService.registerProfile(UUID.fromString(id));

        return ResponseEntity.ok(new SuccessResponseDto<>(
                HttpStatus.OK.value(),
                "User registered successfully",
                null
        ));
    }
}
