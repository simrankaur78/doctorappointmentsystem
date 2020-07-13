package com.nexogichealthcare.models;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Otp {
int id;
int otp;
Timestamp createOn;
Timestamp validUpto;
String email;

public static Map<String, String> tableColumnMappings = new HashMap<>(); 
static {
    tableColumnMappings.put("id", "id");
    tableColumnMappings.put("otp", "otp");
    tableColumnMappings.put("email", "email");
    tableColumnMappings.put("valid_upto", "validUpto");
    tableColumnMappings.put("create_on", "createdOn");
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public int getOtp() {
	return otp;
}
public void setOtp(int otp) {
	this.otp = otp;
}
public Timestamp getCreateOn() {
	return createOn;
}
public void setCreateOn(Timestamp createOn) {
	this.createOn = createOn;
}
public Timestamp getValidUpto() {
	return validUpto;
}
public void setValidUpto(Timestamp validUpto) {
	this.validUpto = validUpto;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Override
public String toString() {
	return "Otp [id=" + id + ", otp=" + otp + ", createOn=" + createOn + ", validUpto=" + validUpto + ", email=" + email
			+ "]";
}

}
