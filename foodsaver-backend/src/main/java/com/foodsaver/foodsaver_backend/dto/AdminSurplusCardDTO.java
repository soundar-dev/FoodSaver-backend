package com.foodsaver.foodsaver_backend.dto;

import com.foodsaver.foodsaver_backend.entity.Surplus;

public class AdminSurplusCardDTO {

    private Long id;
    private String foodName;
    private String location;
    private int quantity;
    private String postedBy;
    private String acceptedByOrg;
    private int expiryHours;
    private Surplus.Status status;

    public AdminSurplusCardDTO(
        Long id,
        String foodName,
        String location,
        int quantity,
        String postedBy,
        String acceptedByOrg,
        int expiryHours,
        Surplus.Status status
    ) {
        this.id = id;
        this.foodName = foodName;
        this.location = location;
        this.quantity = quantity;
        this.postedBy = postedBy;
        this.acceptedByOrg = acceptedByOrg;
        this.expiryHours = expiryHours;
        this.status = status;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getAcceptedByOrg() {
		return acceptedByOrg;
	}

	public void setAcceptedByOrg(String acceptedByOrg) {
		this.acceptedByOrg = acceptedByOrg;
	}

	public int getExpiryHours() {
		return expiryHours;
	}

	public void setExpiryHours(int expiryHours) {
		this.expiryHours = expiryHours;
	}

	public Surplus.Status getStatus() {
		return status;
	}

	public void setStatus(Surplus.Status status) {
		this.status = status;
	}

    // getters
    
    
}
