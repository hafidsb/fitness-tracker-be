package com.hafidsb.fitnesstracker.common.dto.api;

public record SuccessResponseDto<T>(
        int status,
        String message,
        T data
) implements ApiResponse {}
