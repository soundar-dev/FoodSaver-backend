package com.foodsaver.foodsaver_backend.dto;

import java.time.LocalDateTime;

public class SurplusCardDTO {

    public Long id;
    public String foodName;
    public String location;
    public int quantity;
    public LocalDateTime createdAt;
    public int expiryHours;
    public String postedBy;

    public SurplusCardDTO(
        Long id,
        String foodName,
        String location,
        int quantity,
        LocalDateTime createdAt,
        int expiryHours,
        String postedBy
    ) {
        this.id = id;
        this.foodName = foodName;
        this.location = location;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.expiryHours = expiryHours;
        this.postedBy = postedBy;
    }
}
