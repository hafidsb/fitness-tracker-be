package com.hafidsb.fitnesstracker.authservice.service;

import com.hafidsb.fitnesstracker.authservice.dto.LoginRequestDto;
import com.hafidsb.fitnesstracker.authservice.dto.RegisterUserRequestDto;
import com.hafidsb.fitnesstracker.authservice.entity.User;
import com.hafidsb.fitnesstracker.authservice.exception.AuthException;
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
            throw new AuthException("Email is already registered");
        }

        User user = User.builder()
                .email(dto.email())
                .passwordHash(encoder.encode(dto.password()))
                .build();

        return repository.save(user);
    }

    public User loginAndReturn(LoginRequestDto dto) {
        // find user
        User user = repository
                .findByEmail(dto.email())
                .orElseThrow(AuthException::new);

        // encoder.match
        if (!encoder.matches(dto.password(), user.getPasswordHash())) {
            throw new AuthException();
        }
        return user;
    }
}
