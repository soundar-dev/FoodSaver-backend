package com.foodsaver.foodsaver_backend.entity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "surplus")
public class Surplus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "expiry_hours", nullable = false)
    private int expiryHours;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ✅ STORE ORGANIZATION NAME DIRECTLY
    @Column(name = "accepted_by")
    private String acceptedBy;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "posted_by")
    private String postedBy;

    public String getPostedBy() {
        return postedBy;
    }

	@Column(name = "admin_mobile", nullable = false)
	private String adminMobile;
	
	public String getAdminMobile() {
	    return adminMobile;
	}
	
	public void setAdminMobile(String adminMobile) {
	    this.adminMobile = adminMobile;
	}
	
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }



	@PrePersist
	protected void onCreate() {
	    this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
	    if (this.status == null) {
	        this.status = Status.AVAILABLE;
	    }
	}

    public enum Status {
        AVAILABLE,
        ACCEPTED,
        PICKED
    }

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public String getFoodName() { return foodName; }
    public String getLocation() { return location; }
    public int getQuantity() { return quantity; }
    public int getExpiryHours() { return expiryHours; }
    public Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }
	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    
	
    
}



