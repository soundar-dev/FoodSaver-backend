package com.foodsaver.foodsaver_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodsaver.foodsaver_backend.dto.AdminDashboardDTO;
import com.foodsaver.foodsaver_backend.service.SurplusService;

@RestController
@CrossOrigin
public class DashboardController {

    private final SurplusService surplusService;

    public DashboardController(SurplusService surplusService) {
        this.surplusService = surplusService;
    }

    @GetMapping("/api/dashboard/admin")
    public AdminDashboardDTO getAdminDashboard() {
        return surplusService.getAdminDashboardStats();
    }
}
