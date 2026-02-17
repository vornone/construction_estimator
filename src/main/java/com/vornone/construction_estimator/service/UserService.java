package com.vornone.construction_estimator.service;

import com.vornone.construction_estimator.dto.PagedResponse;
import com.vornone.construction_estimator.dto.PaginationRequest;
import com.vornone.construction_estimator.dto.UserDTO;
import com.vornone.construction_estimator.model.User;
import com.vornone.construction_estimator.model.Role;
import com.vornone.construction_estimator.repository.UserRepository;
import com.vornone.construction_estimator.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PagedResponse<UserDTO.UserResponse> getAllUser(PaginationRequest request) {
        return PagedResponse.of(
                userRepository.findAll(PaginationUtils.toPageable(request))
                        .map(this::toResponse)
        );
    }



    public UserDTO.UserResponse updateById(Long id, UserDTO.UpdateUserRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with id {}", id);
                    return new RuntimeException("User not found");
                });

        if (req.username() != null && !req.username().isBlank()) {
            userRepository.findByUsername(req.username())
                    .filter(existing -> !existing.getId().equals(id))
                    .ifPresent(existing -> {
                        log.warn("Username already exists: {}", req.username());
                        throw new RuntimeException("Username already exists");
                    });
            user.setUsername(req.username());
        }

        if (req.email() != null && !req.email().isBlank()) {
            userRepository.findByEmail(req.email())
                    .filter(existing -> !existing.getId().equals(id))
                    .ifPresent(existing -> {
                        log.warn("Email already exists: {}", req.email());
                        throw new RuntimeException("Email already exists");
                    });
            user.setEmail(req.email());
        }

        if (req.company() != null && !req.company().isBlank()) {
            user.setCompany(req.company());
        }

        if (req.password() != null && !req.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(req.password()));
        }

        if (req.role() != null && !req.role().isBlank()) {
            try {
                user.setRole(Role.valueOf(req.role().toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.warn("Invalid role: {}", req.role());
                throw new RuntimeException("Invalid role");
            }
        }

        return toResponse(userRepository.save(user));
    };

    private UserDTO.UserResponse toResponse(User user) {
        return new UserDTO.UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCompany(),
                user.getRole()
        );
    };
}