package com.foodsaver.foodsaver_backend.dto;

public class LoginResponse {
    private String email;
    private String role;

    public LoginResponse(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() { return email; }
    public String getRole() { return role; }
}
