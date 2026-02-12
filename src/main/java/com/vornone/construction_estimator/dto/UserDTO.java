package com.vornone.construction_estimator.dto;

public class UserDTO {
    public record UpdateUserRequest(String username, String email, String password) {
    }
}
