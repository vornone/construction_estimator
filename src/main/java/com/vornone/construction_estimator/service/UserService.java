package com.vornone.construction_estimator.service;

import com.vornone.construction_estimator.dto.UserDTO;
import com.vornone.construction_estimator.model.User;
import com.vornone.construction_estimator.model.Role;
import com.vornone.construction_estimator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void updateById(
            Long id,
            UserDTO.UpdateUserRequest updateUserRequest
    ) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with id {}", id);
                    return new RuntimeException("User not found");
                });

        userRepository.findByUsername(updateUserRequest.username)
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    log.warn("Username already exists: {}", username);
                    throw new RuntimeException("Username already exists");
                });

        userRepository.findByEmail(email)
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    log.warn("Email already exists: {}", email);
                    throw new RuntimeException("Email already exists");
                });

        user.setUsername(username);
        user.setEmail(email);
        user.setCompany(company);

        if (password != null && !password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        try {
            user.setRole(Role.valueOf(role.toUpperCase()));
        } catch (IllegalArgumentException e) {
            log.warn("Invalid role: {}", role);
            throw new RuntimeException("Invalid role");
        }

        userRepository.save(user);
    }
}


