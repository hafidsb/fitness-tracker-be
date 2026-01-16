package com.hafidsb.fitnesstracker.authservice.service;

import com.hafidsb.fitnesstracker.authservice.entity.User;
import com.hafidsb.fitnesstracker.authservice.exception.AuthException;
import com.hafidsb.fitnesstracker.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InternalUserService {

    private final UserRepository repository;

    public User getUserSummary(String id) {
        return repository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new AuthException("User not found"));
    }
}
