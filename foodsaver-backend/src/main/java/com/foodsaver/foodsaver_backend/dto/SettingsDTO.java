package com.foodsaver.foodsaver_backend.dto;

public class SettingsDTO {

    private String organizationName;
    private String contactNumber;

    private boolean emailNotifications;
    private boolean expiryNotifications;
    private boolean weeklyReports;
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

    // getters & setters
    
   
