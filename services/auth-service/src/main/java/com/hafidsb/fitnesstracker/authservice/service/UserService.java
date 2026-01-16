package com.hafidsb.fitnesstracker.authservice.service;

import com.hafidsb.fitnesstracker.authservice.dto.RegisterUserRequestDto;
import com.hafidsb.fitnesstracker.authservice.entity.User;
import com.hafidsb.fitnesstracker.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;

    public User registerAndReturn(RegisterUserRequestDto dto) {
        // business validation
        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        User user = User.builder()
                .email(dto.email())
                .passwordHash(encoder.encode(dto.password()))
                .build();

        return repository.save(user);
    }
}
