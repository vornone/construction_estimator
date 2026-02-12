package com.vornone.construction_estimator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
@Slf4j
public class SecurityConfig {

    @Value("${app.security.password-secret}")
    private String passwordSecret;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new HmacBCryptPasswordEncoder(passwordSecret);
    }

    public static class HmacBCryptPasswordEncoder implements PasswordEncoder {
        private final BCryptPasswordEncoder bCryptEncoder;
        private final String secretKey;
        private static final String ALGORITHM = "HmacSHA256";

        public HmacBCryptPasswordEncoder(String secretKey) {
            this.secretKey = secretKey;
            this.bCryptEncoder = new BCryptPasswordEncoder(12);
        }

        @Override
        public String encode(CharSequence rawPassword) {
            try{
                String hmacHash = hmacSha256(rawPassword.toString(),secretKey);
                return bCryptEncoder.encode(hmacHash);
            }catch (Exception  e){
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            try{
                String hmacHash = hmacSha256(rawPassword.toString(), secretKey);
                return bCryptEncoder.matches(hmacHash, encodedPassword);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        private String hmacSha256(String rawPassword, String secretKey) throws Exception {
            Mac mac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
            mac.init(secretKeySpec);
            byte[] rawHmac = mac.doFinal(rawPassword.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(rawHmac), StandardCharsets.UTF_8);
        }

        @Override
        public boolean upgradeEncoding(String encodedPassword){
            return false;
        }
    }
}
