package com.hafidsb.fitnesstracker.authservice.controller.internal;

import com.hafidsb.fitnesstracker.authservice.entity.User;
import com.hafidsb.fitnesstracker.authservice.service.InternalUserService;
import com.hafidsb.fitnesstracker.common.dto.UserSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
public class InternalUserController {
    private final InternalUserService internalUserService;

    @GetMapping("/{id}")
    public UserSummaryDto getUser(@PathVariable("id") String id) {
        User user = internalUserService.getUserSummary(id);
        return new UserSummaryDto(
                user.getId().toString(),
                user.getEmail(),
                user.getStatus()
        );
    }
}
