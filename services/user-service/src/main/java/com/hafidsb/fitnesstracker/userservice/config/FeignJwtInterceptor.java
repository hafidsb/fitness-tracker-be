package com.hafidsb.fitnesstracker.userservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignJwtInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        log.info("Feign interceptor auth = {}", authentication);

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {

            String tokenValue = jwtAuth.getToken().getTokenValue();

            requestTemplate.header(
                    "Authorization",
                    "Bearer " + tokenValue
            );

            log.info("JWT forwarded to auth-service");

        } else {
            log.warn("NO JWT FOUND IN SECURITY CONTEXT");
        }
    }
}
