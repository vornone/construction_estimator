package com.vornone.construction_estimator.controller;

import com.vornone.construction_estimator.dto.ApiResponse;
import com.vornone.construction_estimator.dto.PaginationRequest;
import com.vornone.construction_estimator.dto.UserDTO;
import com.vornone.construction_estimator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UserDTO.UserResponse>> getAllUser(
            @ModelAttribute PaginationRequest request) {
        return paged(userService.getAllUser(request));
    }

    @PatchMapping("{userId}")
    public ResponseEntity<ApiResponse<UserDTO.UserResponse>> updateById(
            @PathVariable Long userId,
            @RequestBody UserDTO.UpdateUserRequest updateUserRequest) {
        return ok("User updated successfully", userService.updateById(userId, updateUserRequest));
    }
}