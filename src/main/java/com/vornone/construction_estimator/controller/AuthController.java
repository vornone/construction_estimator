package com.vornone.construction_estimator.controller;

import com.vornone.construction_estimator.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/")
public class AuthController extends BaseController{

    public AuthController(UserService userService) {
    }

    @PostMapping("login")
    public ResponseEntity<?> login( ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
