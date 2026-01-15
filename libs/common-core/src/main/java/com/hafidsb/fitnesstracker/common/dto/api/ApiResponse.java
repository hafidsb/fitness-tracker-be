package com.hafidsb.fitnesstracker.common.dto.api;

public sealed interface ApiResponse permits
        SuccessResponseDto,
        ErrorResponseDto
{}
