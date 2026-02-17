package com.vornone.construction_estimator.dto;

public class AuthDTO {
    public record RegisterRequest(String username, String email, String password, String company) {}

    public record LoginRequest(String email, String password) {}

    public record LoginResponse(String token, UserDTO.UserResponse user) {}
}