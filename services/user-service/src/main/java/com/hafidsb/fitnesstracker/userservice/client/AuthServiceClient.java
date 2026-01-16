package com.hafidsb.fitnesstracker.userservice.client;

import com.hafidsb.fitnesstracker.common.dto.UserSummaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "auth-service",
        url = "${services.auth.base-url}"
)
public interface AuthServiceClient {

    @GetMapping("/internal/users/{id}")
    UserSummaryDto getUserById(@PathVariable("id") String id);
}
