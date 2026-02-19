package com.vornone.construction_estimator.config;

import com.vornone.construction_estimator.model.Role;
import com.vornone.construction_estimator.model.User;
import com.vornone.construction_estimator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Delete old users if they exist (one-time migration)
        userRepository.deleteAll();  // Add this line

        if(userRepository.count()==0){
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .email("admin@gmail.com")
                    .role(Role.ADMIN)
                    .company("Asymtote")
                    .build();

            User contractor = User.builder()
                    .username("john_contractor")
                    .password(passwordEncoder.encode("contractor"))
                    .email("john@abcconstruction.com")
                    .role(Role.USER)
                    .company("ABC Construction")
                    .build();

            User client = User.builder()
                    .username("mary_client")
                    .password(passwordEncoder.encode("client"))
                    .email("mary@client.com")
                    .role(Role.USER)
                    .company("Private Client")
                    .build();

            userRepository.save(admin);
            userRepository.save(contractor);
            userRepository.save(client);

            System.out.println("✅ Sample users created!");
        } else {
            System.out.println("Users already exist");
        }
    }
}
