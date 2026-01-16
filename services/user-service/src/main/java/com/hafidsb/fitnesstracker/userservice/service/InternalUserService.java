package com.hafidsb.fitnesstracker.userservice.service;

import com.hafidsb.fitnesstracker.userservice.entity.User;
import com.hafidsb.fitnesstracker.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InternalUserService {

    private final UserRepository repository;

    public void registerProfile(UUID id) {
        repository.save(
                User.builder()
                        .id(id)
                        .status(User.Status.ACTIVE)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );
    }
}
