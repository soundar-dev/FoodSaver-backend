package com.foodsaver.foodsaver_backend.service;

import org.springframework.stereotype.Service;

import com.foodsaver.foodsaver_backend.dto.LoginRequest;
import com.foodsaver.foodsaver_backend.dto.LoginResponse;
import com.foodsaver.foodsaver_backend.entity.User;
import com.foodsaver.foodsaver_backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new LoginResponse(
                user.getEmail(),
                user.getRole().name()
        );
    }
}
