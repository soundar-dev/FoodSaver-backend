package com.foodsaver.foodsaver_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Surplus> add(@RequestBody Surplus surplus) {
        return ResponseEntity.ok(service.addSurplus(surplus));
    }

    /* ---------------- GET ALL SURPLUS ---------------- */
    @GetMapping("/all")
    public ResponseEntity<List<Surplus>> all() {
        return ResponseEntity.ok(service.getAll());
    }

    /* ---------------- DELETE SURPLUS (ADMIN) ---------------- */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurplus(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /* ---------------- ACCEPT SURPLUS (USER/NGO) ---------------- */
    @PutMapping("/{id}/accept")
    public ResponseEntity<Void> acceptSurplus(@PathVariable Long id) {
        service.updateStatus(id, Surplus.Status.ACCEPTED);
        return ResponseEntity.ok().build();
    }

    /* ---------------- MARK AS PICKED (ADMIN) ---------------- */
    @PutMapping("/{id}/picked")
    public ResponseEntity<Void> markPicked(@PathVariable Long id) {
        service.updateStatus(id, Surplus.Status.PICKED);
        return ResponseEntity.ok().build();
    }
}
