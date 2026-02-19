package com.vornone.construction_estimator.controller;

import com.vornone.construction_estimator.dto.ApiResponse;
import com.vornone.construction_estimator.dto.AuthDTO;
import com.vornone.construction_estimator.service.AuthService;
import com.vornone.construction_estimator.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/")
public class AuthController extends BaseController{
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("login")
    public ResponseEntity<ApiResponse<AuthDTO.LoginResponse>> login(
            @RequestBody AuthDTO.LoginRequest request) {  // @RequestBody here
        return ok("Login successful", authService.login(request));
    }


}
