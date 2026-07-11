package com.project.fitness.service;

import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class UserService {

    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();
        // rest all thing not mentioned in builder will automatically become null
//        User user = new User(
//                null,
//                registerRequest.getEmail(),
//                registerRequest.getPassword(),
//                registerRequest.getFirstName(),
//                registerRequest.getLastName(),
//                Instant.parse("2025-12-03T10:15:30.00Z")
//                        .atZone(ZoneOffset.UTC)
//                        .toLocalDateTime(),
//                Instant.parse("2025-12-03T10:15:30.00Z")
//                        .atZone(ZoneOffset.UTC)
//                        .toLocalDateTime(),
//                List.of(),
//                List.of()
//        );
        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User savedUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());

        return userResponse;
    }
}
