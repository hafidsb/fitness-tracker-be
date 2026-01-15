package com.hafidsb.fitnesstracker.common.dto.api;

public record SuccessResponseDto<T>(
        int status,
        String statusMessage,
        T data
) implements ApiResponse {}
