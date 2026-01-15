package com.hafidsb.fitnesstracker.common.dto.api;

public record ErrorResponseDto(
        int status,
        String statusMessage,
        String errorMessage
) implements ApiResponse {}
