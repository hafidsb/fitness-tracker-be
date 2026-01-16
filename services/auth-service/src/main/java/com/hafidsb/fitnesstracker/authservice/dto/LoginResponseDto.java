package com.hafidsb.fitnesstracker.authservice.dto;

import java.util.UUID;

public record LoginResponseDto(
        UUID id,
        String email,
        String accessToken
) {}
