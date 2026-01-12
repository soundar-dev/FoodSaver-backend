package com.foodsaver.foodsaver_backend.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodsaver.foodsaver_backend.dto.SettingsDTO;
import com.foodsaver.foodsaver_backend.entity.User;
import com.foodsaver.foodsaver_backend.repository.UserRepository;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin
public class SettingsController {

    private final UserRepository userRepo;

    public SettingsController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // GET settings
    @GetMapping
    public SettingsDTO getSettings(@RequestParam String email) {

        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        SettingsDTO dto = new SettingsDTO();
        dto.setOrganizationName(user.getOrganizationName());
        dto.setContactNumber(user.getContactNumber());
        dto.setEmailNotifications(user.isEmailNotifications());
        dto.setExpiryNotifications(user.isExpiryNotifications());
        dto.setWeeklyReports(user.isWeeklyReports());

        return dto;
    }

    // SAVE settings
    @PutMapping
    public void saveSettings(
        @RequestParam String email,
        @RequestBody SettingsDTO dto
    ) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        user.setOrganizationName(dto.getOrganizationName());
        user.setContactNumber(dto.getContactNumber());
        user.setEmailNotifications(dto.isEmailNotifications());
        user.setExpiryNotifications(dto.isExpiryNotifications());
        user.setWeeklyReports(dto.isWeeklyReports());

        userRepo.save(user);
    }
}
