package com.vornone.construction_estimator.controller;

import com.vornone.construction_estimator.dto.UserDTO;
import com.vornone.construction_estimator.model.User;
import com.vornone.construction_estimator.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import
@Controller
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    @PatchMapping("update/{userId}")
    public User updateById (@PathVariable Long userId, @RequestBody UserDTO.UpdateUserRequest updateUserRequest) {
        return userService.updateById(userId, updateUserRequest );
    }

}
