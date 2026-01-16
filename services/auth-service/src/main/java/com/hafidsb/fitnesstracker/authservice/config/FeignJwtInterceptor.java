package com.hafidsb.fitnesstracker.authservice.config;

import com.hafidsb.fitnesstracker.authservice.service.JwtService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignJwtInterceptor implements RequestInterceptor {

    private final JwtService jwtService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String internalToken = jwtService.generateInternalToken("auth-service");

        requestTemplate.header(
                "Authorization",
                "Bearer " + internalToken
        );
    }
}
