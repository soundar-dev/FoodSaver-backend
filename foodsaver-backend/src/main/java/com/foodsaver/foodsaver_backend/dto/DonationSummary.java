package com.foodsaver.foodsaver_backend.dto;

public class DonationSummary {

    public double totalValue;
    public int mealsDelivered;
    public long pending;

    public DonationSummary(double totalValue, int mealsDelivered, long pending) {
        this.totalValue = totalValue;
        this.mealsDelivered = mealsDelivered;
        this.pending = pending;
    }
}
