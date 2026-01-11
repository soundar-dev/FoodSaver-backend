package com.foodsaver.foodsaver_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foodsaver.foodsaver_backend.dto.AdminDashboardDTO;
import com.foodsaver.foodsaver_backend.entity.Surplus;
import com.foodsaver.foodsaver_backend.repository.SurplusRepository;

import jakarta.transaction.Transactional;

@Service
public class SurplusService {

    private final SurplusRepository repo;

    public SurplusService(SurplusRepository repo) {
        this.repo = repo;
    }

    public Surplus addSurplus(Surplus surplus) {
        // ✅ enum, not string
        surplus.setStatus(Surplus.Status.AVAILABLE);
        return repo.save(surplus);
    }

    public List<Surplus> getAll() {
        return repo.findAll();
    }
    
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
    
    public void updateStatus(Long id, Surplus.Status status) {
        Surplus surplus = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Surplus not found"));

        surplus.setStatus(status);
        repo.save(surplus);
    }
    
    @Transactional
    public void autoCleanPickedAfterHours(int hours) {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(hours);
        int deleted = repo.deletePickedOlderThan(cutoff);
        System.out.println("🧹 Auto-clean deleted " + deleted + " PICKED items");
    }
    
    public AdminDashboardDTO getAdminDashboardStats() {

        LocalDateTime todayStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime expiredCutoff = LocalDateTime.now().minusHours(8);

        long addedToday = repo.countByCreatedAtAfter(todayStart);
        long awaitingPickup = repo.countByStatus(Surplus.Status.ACCEPTED);
        long picked = repo.countByStatus(Surplus.Status.PICKED);
        long expired = repo.countExpired(expiredCutoff);

        return new AdminDashboardDTO(
                addedToday,
                awaitingPickup,
                picked,
                expired
        );
    }




}
