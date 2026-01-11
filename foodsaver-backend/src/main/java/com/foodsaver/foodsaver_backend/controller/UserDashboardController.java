package com.foodsaver.foodsaver_backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.foodsaver.foodsaver_backend.entity.Surplus;
import com.foodsaver.foodsaver_backend.repository.SurplusRepository;
import com.foodsaver.foodsaver_backend.service.SurplusService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserDashboardController {

    private final SurplusRepository repo;
    private final SurplusService service;

    public UserDashboardController(SurplusRepository repo, SurplusService service) {
        this.repo = repo;
        this.service = service;
    }

    /* =========================
       DASHBOARD SUMMARY
       ========================= */
    @GetMapping("/dashboard/summary")
    public Map<String, Long> getUserDashboardSummary() {

        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
        LocalDateTime expiringSoon = LocalDateTime.now().minusHours(6);

        long available = repo.countByStatus(Surplus.Status.AVAILABLE);
        long accepted = repo.countByStatus(Surplus.Status.ACCEPTED);
        long expiringSoonCount = repo.countExpired(expiringSoon);

        return Map.of(
            "availableFood", available,
            "acceptedFood", accepted,
            "expiringSoon", expiringSoonCount
        );
    }

    /* =========================
       AVAILABLE FOOD LIST
       ========================= */
    @GetMapping("/surplus")
    public List<Surplus> getAvailableSurplus() {
        return repo.findAll()
                   .stream()
                   .filter(s ->
                       s.getStatus() == Surplus.Status.AVAILABLE ||
                       s.getStatus() == Surplus.Status.ACCEPTED
                   )
                   .toList();
    }

    /* =========================
       ACCEPT FOOD
       ========================= */
    @PutMapping("/surplus/{id}/accept")
    public void acceptSurplus(@PathVariable Long id) {
        service.updateStatus(id, Surplus.Status.ACCEPTED);
    }
}
