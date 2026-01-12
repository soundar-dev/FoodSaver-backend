package com.foodsaver.foodsaver_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.foodsaver.foodsaver_backend.entity.Surplus;
import com.foodsaver.foodsaver_backend.repository.SurplusRepository;

@RestController
@RequestMapping("/api/pickup")
@CrossOrigin
public class PickupController {

    private final SurplusRepository surplusRepository;

    public PickupController(SurplusRepository surplusRepository) {
        this.surplusRepository = surplusRepository;
    }

    /* =============================
       SUMMARY COUNTS (TOP CARDS)
       ============================= */
    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        long accepted = surplusRepository.findAll()
                .stream()
                .filter(s -> s.getStatus() == Surplus.Status.ACCEPTED)
                .count();

        long completed = surplusRepository.findAll()
                .stream()
                .filter(s -> s.getStatus() == Surplus.Status.PICKED)
                .count();

        return Map.of(
                "accepted", accepted,
                "inProgress", accepted, // logical mapping
                "completed", completed
        );
    }

    /* =============================
       TABLE DATA
       ============================= */
    @GetMapping("/list")
    public List<Surplus> getPickupList(
            @RequestParam(required = false) String status) {

        if (status == null || status.equalsIgnoreCase("ALL")) {
            return surplusRepository.findAll();
        }

        return surplusRepository.findAll()
                .stream()
                .filter(s -> s.getStatus().name().equalsIgnoreCase(status))
                .toList();
    }
}
