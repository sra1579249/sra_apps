package com.vogodt.form;

import java.util.Date;

public class BikeRentalForm {
	private String fullname;
	private Date dob;
	private String gender;
	private String mobileNo;
	private String emailAddress;
	private String drivingLicenseNo;
	private int bikeNo;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	public int getBikeNo() {
		return bikeNo;
	}

	public void setBikeNo(int bikeNo) {
		this.bikeNo = bikeNo;
	}

}
