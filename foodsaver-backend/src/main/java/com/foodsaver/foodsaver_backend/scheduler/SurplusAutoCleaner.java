package com.foodsaver.foodsaver_backend.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.foodsaver.foodsaver_backend.entity.Surplus;
import com.foodsaver.foodsaver_backend.repository.SurplusRepository;

@Component
public class SurplusAutoCleaner {

    private final SurplusRepository repo;

    public SurplusAutoCleaner(SurplusRepository repo) {
        this.repo = repo;
    }

    // Runs every 5 minutes
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void deleteExpiredSurplus() {
        LocalDateTime now = LocalDateTime.now();

        List<Surplus> all = repo.findAll();

        for (Surplus surplus : all) {
            LocalDateTime expiryTime =
                surplus.getCreatedAt().plusHours(surplus.getExpiryHours());

            if (now.isAfter(expiryTime)) {
                repo.delete(surplus);
            }
        }
    }
}
