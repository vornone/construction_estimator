package com.vornone.construction_estimator.dto;
import com.vornone.construction_estimator.model.Role;

public class UserDTO {

    public record UserResponse(Long id, String username, String email, String company, Role role) {}

    public record UpdateUserRequest(String username, String email, String password, String company, String role) {}

    public record CreateUserRequest(String username, String email, String password, String company, String role) {}

    public record CreateUserResponse(String username, String email, String password, String company, String role) {}
}