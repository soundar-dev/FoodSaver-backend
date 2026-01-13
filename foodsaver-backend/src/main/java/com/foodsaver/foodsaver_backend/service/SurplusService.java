package com.foodsaver.foodsaver_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foodsaver.foodsaver_backend.dto.AdminDashboardDTO;
import com.foodsaver.foodsaver_backend.dto.AdminSurplusCardDTO;
import com.foodsaver.foodsaver_backend.entity.Surplus;
import com.foodsaver.foodsaver_backend.entity.User;
import com.foodsaver.foodsaver_backend.repository.SurplusRepository;
import com.foodsaver.foodsaver_backend.repository.UserRepository;

@Service
public class SurplusService {

    private final SurplusRepository surplusRepo;
    private final UserRepository userRepo;

    public SurplusService(SurplusRepository surplusRepo, UserRepository userRepo) {
        this.surplusRepo = surplusRepo;
        this.userRepo = userRepo;
    }

    /* ---------- ADD SURPLUS (ADMIN) ---------- */
    public Surplus addSurplus(Surplus surplus, String adminEmail) {

        User admin = userRepo.findByEmail(adminEmail)
            .orElseThrow(() -> new RuntimeException("Admin not found"));

        surplus.setCreatedBy(admin.getEmail());              // 🔐 ownership
        surplus.setPostedBy(admin.getOrganizationName());   // 🏢 display
        surplus.setStatus(Surplus.Status.AVAILABLE);

        return surplusRepo.save(surplus);
    }

    /* ---------- ADMIN DASHBOARD (ISOLATED) ---------- */
    public List<Surplus> getAdminSurplus(String adminEmail) {
        return surplusRepo.findByCreatedBy(adminEmail);
    }

    public List<AdminSurplusCardDTO> getAdminCards(String adminEmail) {
        return surplusRepo.findAllForAdmin(adminEmail);
    }

    public AdminDashboardDTO getAdminDashboardStats(String adminEmail) {

        LocalDateTime todayStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime now = LocalDateTime.now();

        List<Surplus> adminSurplus = surplusRepo.findByCreatedBy(adminEmail);

        long addedToday = adminSurplus.stream()
            .filter(s -> s.getCreatedAt() != null)
            .filter(s -> s.getCreatedAt().isAfter(todayStart))
            .count();

        long awaitingPickup = adminSurplus.stream()
            .filter(s -> s.getStatus() == Surplus.Status.ACCEPTED)
            .count();

        long picked = adminSurplus.stream()
            .filter(s -> s.getStatus() == Surplus.Status.PICKED)
            .count();

        long expired = adminSurplus.stream()
            .filter(s -> s.getStatus() != Surplus.Status.PICKED)
            .filter(s ->
                s.getCreatedAt()
                 .plusHours(s.getExpiryHours())
                 .isBefore(now)
            )
            .count();

        return new AdminDashboardDTO(
            addedToday,
            awaitingPickup,
            picked,
            expired
        );
    }

    /* ---------- USER / NGO ---------- */
    public List<Surplus> getSurplusForUsers() {
        return surplusRepo.findForUsers();
    }

    /* ---------- COMMON ---------- */

    public void deleteById(Long id) {
        surplusRepo.deleteById(id);
    }

   public void acceptSurplus(Long id, String email) {

    User user = userRepo.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    surplusRepo.findById(id).ifPresent(s -> {
        s.setStatus(Surplus.Status.ACCEPTED);

        // ✅ SAVE ORGANIZATION NAME
        s.setAcceptedBy(user.getOrganizationName());

        surplusRepo.save(s);
    });
}

    public void updateStatus(Long id, Surplus.Status status) {
        surplusRepo.findById(id).ifPresent(s -> {
            s.setStatus(status);
            surplusRepo.save(s);
        });
    }
}

