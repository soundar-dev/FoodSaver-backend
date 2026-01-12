package com.foodsaver.foodsaver_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.foodsaver.foodsaver_backend.dto.AdminSurplusCardDTO;
import com.foodsaver.foodsaver_backend.entity.Surplus;
import com.foodsaver.foodsaver_backend.service.SurplusService;

@RestController
@RequestMapping("/api/surplus")
@CrossOrigin(origins = "*")
public class SurplusController {

    private final SurplusService service;

    public SurplusController(SurplusService service) {
        this.service = service;
    }

    /* ---------------- ADD SURPLUS (ADMIN) ---------------- */
    @PostMapping("/add")
    public ResponseEntity<Surplus> add(
            @RequestBody Surplus surplus,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
            service.addSurplus(surplus, authentication.getName())
        );
    }

    /* ---------------- ADMIN: SURPLUS CARDS ---------------- */
    @GetMapping("/admin/cards")
    public List<AdminSurplusCardDTO> getAllForAdmin(Authentication authentication) {
        return service.getAdminCards(authentication.getName());
    }

    /* ---------------- DELETE SURPLUS (ADMIN) ---------------- */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurplus(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /* ---------------- ACCEPT SURPLUS (USER / NGO) ---------------- */
    @PutMapping("/{id}/accept")
    public ResponseEntity<Void> acceptSurplus(
            @PathVariable Long id,
            @RequestParam String email
    ) {
        service.acceptSurplus(id, email);
        return ResponseEntity.ok().build();
    }

    /* ---------------- MARK AS PICKED (ADMIN) ---------------- */
    @PutMapping("/{id}/picked")
    public ResponseEntity<Void> markPicked(@PathVariable Long id) {
        service.updateStatus(id, Surplus.Status.PICKED);
        return ResponseEntity.ok().build();
    }

    /* ---------------- USER: AVAILABLE SURPLUS ---------------- */
    @GetMapping("/available")
    public ResponseEntity<List<Surplus>> availableForUsers() {
        return ResponseEntity.ok(service.getSurplusForUsers());
    }
}
