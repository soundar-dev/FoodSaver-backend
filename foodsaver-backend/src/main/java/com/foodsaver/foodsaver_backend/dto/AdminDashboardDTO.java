package com.foodsaver.foodsaver_backend.dto;

public class AdminDashboardDTO {

    private long addedToday;
    private long awaitingPickup;
    private long picked;
    private long expired;

    public AdminDashboardDTO(long addedToday, long awaitingPickup, long picked, long expired) {
        this.addedToday = addedToday;
        this.awaitingPickup = awaitingPickup;
        this.picked = picked;
        this.expired = expired;
    }

    public long getAddedToday() {
        return addedToday;
    }

    public long getAwaitingPickup() {
        return awaitingPickup;
    }

    public long getPicked() {
        return picked;
    }

    public long getExpired() {
        return expired;
    }
}
