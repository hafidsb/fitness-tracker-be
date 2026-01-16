package com.hafidsb.fitnesstracker.userservice.service;

import com.hafidsb.fitnesstracker.common.dto.UserSummaryDto;
import com.hafidsb.fitnesstracker.userservice.client.AuthServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthServiceClient client;

    public UserSummaryDto getUserFromAuth(String userId) {
        return client.getUserById(userId);
    }
}
