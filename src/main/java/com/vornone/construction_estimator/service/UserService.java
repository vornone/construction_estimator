package com.vornone.construction_estimator.service;

import com.vornone.construction_estimator.model.User;
import com.vornone.construction_estimator.model.Role;
import com.vornone.construction_estimator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User updateById (Long id, String username, String password, String role, String email, String company ) {

        if(userRepository.findById(id).isEmpty()){
            log.warn("User not found");
            throw new RuntimeException("User not found");
        }

        if(userRepository.findByUsername(username).isPresent()){
            log.warn("Username Already Exists");
            throw new RuntimeException("Username Already Exists");
        }
        if (userRepository.findByEmail(email).isPresent()){
            log.warn("Email Already Exists");
            throw new RuntimeException("Email Already Exists");
        }

        return null;
    }

}
