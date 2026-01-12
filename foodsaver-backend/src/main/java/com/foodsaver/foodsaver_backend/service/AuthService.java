package com.foodsaver.foodsaver_backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodsaver.foodsaver_backend.dto.LoginRequest;
import com.foodsaver.foodsaver_backend.dto.LoginResponse;
import com.foodsaver.foodsaver_backend.entity.User;
import com.foodsaver.foodsaver_backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔍 DEBUG (you can remove later)
        System.out.println("RAW PASSWORD = " + request.getPassword());
        System.out.println("HASH FROM DB = " + user.getPassword());
        System.out.println("MATCH = " +
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                )
        );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid credentials");
        }

        return new LoginResponse(
                user.getEmail(),
                user.getRole().name()
        );
    }
}
