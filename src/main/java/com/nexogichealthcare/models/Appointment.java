package com.nexogichealthcare.models;

import java.util.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Appointment {
 int pid;
 int id;
 String patientName;
 String patientEmail;
 String patientPhone;
 Date date;
 public static Map<String, String> tableColumnMappings = new HashMap<>(); 
 static {
     tableColumnMappings.put("p_id", "pid");
     tableColumnMappings.put("id", "id");
     tableColumnMappings.put("patient_name", "patientName");
     tableColumnMappings.put("patient_email", "patientEmail");
     tableColumnMappings.put("patient_phone", "patientPhone");
     tableColumnMappings.put("date", "date");
 }

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getPatientName() {
	return patientName;
}
public void setPatientName(String patientName) {
	this.patientName = patientName;
}
public String getPatientEmail() {
	return patientEmail;
}
public void setPatientEmail(String patientEmail) {
	this.patientEmail = patientEmail;
}
public String getPatientPhone() {
	return patientPhone;
}
public void setPatientPhone(String patientPhone) {
	this.patientPhone = patientPhone;
}


public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

@Override
public String toString() {
	return "Appointment [pid=" + pid + ", id=" + id + ", patientName=" + patientName + ", patientEmail="
			+ patientEmail + ", patientPhone=" + patientPhone + ", date=" + date + "]";
}
 
}
