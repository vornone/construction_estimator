package com.vornone.construction_estimator.service;

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

    public User register(String username, String password, String email, String company){
        log.info("Registering New User Where Username = " + username);

        if(userRepository.findByUsername(username).isPresent()){
            log.warn("Username Already Exists");
            throw new RuntimeException("Username Already Exists");
        }

        if(userRepository.findByEmail(email).isPresent()){
            log.warn("Email Already Exists");
            throw new RuntimeException("Email Already Exists");
        }
        String encodedPassword = passwordEncoder.encode(password);
        log.debug("Encoded password = " + encodedPassword);
        User user = User.builder().
                    username(username)
                .password(encodedPassword)
                .email(email)
                .role(Role.ADMIN)
                .company(company).build();

        User savedUser = userRepository.save(user);
        log.info("User Registered Successfully");
        return savedUser;
    };
}
