package com.foodsaver.foodsaver_backend.entity;

import jakarta.persistence.Column;

public class Settings {
	@Column(nullable = false)
	private String organizationName;

	@Column
	private String contactNumber;

	// preferences
	private boolean emailNotifications = true;
	private boolean expiryNotifications = true;
	private boolean weeklyReports = false;
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
	
	

}
