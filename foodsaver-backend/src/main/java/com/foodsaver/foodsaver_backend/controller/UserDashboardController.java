package com.foodsaver.foodsaver_backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.foodsaver.foodsaver_backend.entity.Surplus;
import com.foodsaver.foodsaver_backend.repository.SurplusRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserDashboardController {

    private final SurplusRepository repo;

    public UserDashboardController(SurplusRepository repo) {
        this.repo = repo;
    }

    /* =========================
       DASHBOARD SUMMARY
       ========================= */
    @GetMapping("/dashboard/summary")
    public Map<String, Long> summary(Authentication auth) {

        String email = auth.getName();
        LocalDateTime now = LocalDateTime.now();

        long available = repo.countByStatus(Surplus.Status.AVAILABLE);
        long accepted  = repo.countByAcceptedBy(email);

        long expiringSoon = repo.findAll().stream()
            .filter(s -> s.getStatus() == Surplus.Status.AVAILABLE)
            .filter(s ->
                s.getCreatedAt()
                 .plusHours(s.getExpiryHours())
                 .isBefore(now.plusHours(2))
            )
            .count();

        return Map.of(
            "availableFood", available,
            "acceptedFood", accepted,
            "expiringSoon", expiringSoon
        );
    }

    /* =========================
       AVAILABLE FOOD (USER)
       ========================= */
    @GetMapping("/surplus")
    public List<Surplus> getAvailable() {
        return repo.findAll().stream()
            .filter(s -> s.getStatus() == Surplus.Status.AVAILABLE)
            .toList();
    }

    /* =========================
       ACCEPT FOOD
       ========================= */
    @PutMapping("/surplus/{id}/accept")
    public void accept(@PathVariable Long id, Authentication auth) {

        repo.findById(id).ifPresent(s -> {
            s.setStatus(Surplus.Status.ACCEPTED);
            s.setAcceptedBy(auth.getName()); // NGO email or name
            repo.save(s);
        });
    }
}
