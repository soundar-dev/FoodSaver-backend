package com.foodsaver.foodsaver_backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodsaver.foodsaver_backend.config.JwtUtil;
import com.foodsaver.foodsaver_backend.dto.AuthRequest;
import com.foodsaver.foodsaver_backend.dto.AuthResponse;
import com.foodsaver.foodsaver_backend.entity.User;
import com.foodsaver.foodsaver_backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }
}
