package com.foodsaver.foodsaver_backend.controller;

import org.springframework.web.bind.annotation.*;

import com.foodsaver.foodsaver_backend.dto.LoginRequest;
import com.foodsaver.foodsaver_backend.dto.LoginResponse;
import com.foodsaver.foodsaver_backend.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public Map<String, String> logout() {
        return Map.of("message", "Logged out successfully");
    }
}
