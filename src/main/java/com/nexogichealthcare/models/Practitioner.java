package com.nexogichealthcare.models;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Practitioner {
	int p_id;
	String firstname;
	String lastname;
	String email;
	String password;
	String phone;
	String speciality;
	String state;
	int verifyStatus;
	Timestamp updatedOn;
	Timestamp createdOn;
	String registrationYear;
	String registrationCouncil;
	String registrationId;//registration_id
	String about;
	public static Map<String, String> tableColumnMappings = new HashMap<>();
	static {

		tableColumnMappings.put("firstname", "firstname");
		//tableColumnMappings.put("middle_name", "middleName");
		tableColumnMappings.put("lastname", "lastname");
		//tableColumnMappings.put("registration_number", "registrationNumber");
		tableColumnMappings.put("state", "state");
		//tableColumnMappings.put("profile_summary", "profileSummary");
		//tableColumnMappings.put("profile_pic_url", "profilePicUrl");
		//tableColumnMappings.put("profile_claimed", "profileClaimed");
		//tableColumnMappings.put("profile_verified", "profileVerified");
		tableColumnMappings.put("createdon", "createdOn");
		tableColumnMappings.put("updatedon", "updatedOn");
		tableColumnMappings.put("speciality", "speciality");
		tableColumnMappings.put("phone", "phone");
		tableColumnMappings.put("email", "email");
		tableColumnMappings.put("password", "password");
		tableColumnMappings.put("p_id", "p_id");
		tableColumnMappings.put("verify_status", "verifyStatus");
		tableColumnMappings.put("registration_id", "registrationId");//registration_id
		tableColumnMappings.put("registration_year", "registrationYear");
		tableColumnMappings.put("registration_council", "registrationCouncil");
		tableColumnMappings.put("about", "about");
		}





@Override
	public String toString() {
		return "Practitioner [p_id=" + p_id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", speciality=" + speciality + ", state=" + state
				+ ", verifyStatus=" + verifyStatus + ", updatedOn=" + updatedOn + ", createdOn=" + createdOn
				+ ", registrationYear=" + registrationYear + ", registrationCouncil=" + registrationCouncil
				+ ", registrationId=" + registrationId + ", about=" + about + "]";
	}



public int getP_id() {
	return p_id;
}



public void setP_id(int p_id) {
	this.p_id = p_id;
}



public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getSpeciality() {
	return speciality;
}
public void setSpeciality(String speciality) {
	this.speciality = speciality;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}

public Timestamp getUpdatedOn() {
	return updatedOn;
}

public void setUpdatedOn(Timestamp updatedOn) {
	this.updatedOn = updatedOn;
}

public Timestamp getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Timestamp createdOn) {
	this.createdOn = createdOn;
}

public int getVerifyStatus() {
	return verifyStatus;
}

public void setVerifyStatus(int verifyStatus) {
	this.verifyStatus = verifyStatus;
}

public String getRegistrationYear() {
	return registrationYear;
}

public void setRegistrationYear(String registrationYear) {
	this.registrationYear = registrationYear;
}


public String getRegistrationCouncil() {
	return registrationCouncil;
}

public void setRegistrationCouncil(String registrationCouncil) {
	this.registrationCouncil = registrationCouncil;
}

public String getRegistrationId() {
	return registrationId;
}

public void setRegistrationId(String registrationId) {
	this.registrationId = registrationId;
}

public String getAbout() {
	return about;
}

public void setAbout(String about) {
	this.about = about;
}



}
