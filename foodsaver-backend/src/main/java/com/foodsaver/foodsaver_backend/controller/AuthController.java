// package com.foodsaver.foodsaver_backend.controller;

// import org.springframework.web.bind.annotation.*;

// import com.foodsaver.foodsaver_backend.dto.AuthRequest;
// import com.foodsaver.foodsaver_backend.dto.AuthResponse;
// import com.foodsaver.foodsaver_backend.service.AuthService;

// @RestController
// @RequestMapping("/api/auth")
// @CrossOrigin(origins = "*")
// public class AuthController {

//     private final AuthService authService;

//     public AuthController(AuthService authService) {
//         this.authService = authService;
//     }

//     @PostMapping("/login")
//     public AuthResponse login(@RequestBody AuthRequest request) {
//         return authService.login(request);
//     }
// }

package com.foodsaver.foodsaver_backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.foodsaver.foodsaver_backend.dto.AuthRequest;
import com.foodsaver.foodsaver_backend.dto.AuthResponse;
import com.foodsaver.foodsaver_backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    // ✅ Health check for UptimeRobot
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}

