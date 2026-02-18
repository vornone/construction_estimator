package com.vornone.construction_estimator.service;

import com.vornone.construction_estimator.dto.AuthDTO;
import com.vornone.construction_estimator.dto.UserDTO;
import com.vornone.construction_estimator.model.Role;
import com.vornone.construction_estimator.model.User;
import com.vornone.construction_estimator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserDTO.UserResponse register(AuthDTO.RegisterRequest req) {
        log.info("Registering new user: {}", req.username());

        if (userRepository.findByUsername(req.username()).isPresent()) {
            log.warn("Username already exists: {}", req.username());
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.findByEmail(req.email()).isPresent()) {
            log.warn("Email already exists: {}", req.email());
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(req.username())
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .company(req.company())
                .role(Role.USER)
                .build();

        User saved = userRepository.save(user);
        log.info("User registered successfully: {}", saved.getId());
        return toResponse(saved);
    }

    public AuthDTO.LoginResponse login(AuthDTO.LoginRequest req) {
        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> {
                    log.warn("User not found: {}", req.email());
                    return new RuntimeException("Invalid email or password");
                });

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            log.warn("Invalid password for user: {}", req.email());
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        log.info("User logged in successfully: {}", user.getId());
        return new AuthDTO.LoginResponse(token, toResponse(user));
    }

    private UserDTO.UserResponse toResponse(User user) {
        return new UserDTO.UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCompany(),
                user.getRole()
        );
    }




}