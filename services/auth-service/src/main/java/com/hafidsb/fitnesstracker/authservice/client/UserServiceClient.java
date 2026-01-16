package com.hafidsb.fitnesstracker.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "auth-service",
        url = "${services.auth.base-url}"
)
public interface UserServiceClient {

    @PostMapping("/internal/auth/{id}")
    void registerProfile(@PathVariable("id") String id);
}
