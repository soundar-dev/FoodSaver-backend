package com.foodsaver.foodsaver_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(nullable = false)
    private String organizationName;

    @Column
    private String contactNumber;

    // preferences
    private boolean emailNotifications = true;
    private boolean expiryNotifications = true;
    private boolean weeklyReports = false;


    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    public enum Role {
        ADMIN, USER
    }

    public enum OrganizationType {
        MARRIAGE_HALL,
        PARTY_HALL,
        NGO,
        TRUST,
        ORPHANAGE
    }

    // getters & setters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public boolean isEmailNotifications() {
		return emailNotifications;
	}
	public void setEmailNotifications(boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}
	public boolean isExpiryNotifications() {
		return expiryNotifications;
	}
	public void setExpiryNotifications(boolean expiryNotifications) {
		this.expiryNotifications = expiryNotifications;
	}
	public boolean isWeeklyReports() {
		return weeklyReports;
	}
	public void setWeeklyReports(boolean weeklyReports) {
		this.weeklyReports = weeklyReports;
	}
	public OrganizationType getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}
    
}
