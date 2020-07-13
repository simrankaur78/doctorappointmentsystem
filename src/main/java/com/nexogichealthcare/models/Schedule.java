package com.nexogichealthcare.models;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
int id;
int pid;
Time starttime;
Time endtime;
Time duration;
int charges;
String officeaddress;
String officephone;
public static Map<String, String> tableColumnMappings = new HashMap<>(); 
static {
    tableColumnMappings.put("p_id", "pid");
    tableColumnMappings.put("id", "id");
    tableColumnMappings.put("starttime", "starttime");
    tableColumnMappings.put("endtime", "endtime");
    tableColumnMappings.put("duration", "duration");
    tableColumnMappings.put("charges", "charges");
    tableColumnMappings.put("duration", "duration");
    tableColumnMappings.put("officeaddress", "officeaddress");
    tableColumnMappings.put("officephone", "officephone");
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
public Time getStarttime() {
	return starttime;
}
public void setStarttime(Time starttime) {
	this.starttime = starttime;
}
public Time getEndtime() {
	return endtime;
}
public void setEndtime(Time endtime) {
	this.endtime = endtime;
}
public Time getDuration() {
	return duration;
}
public void setDuration(Time duration) {
	this.duration = duration;
}
public int getCharges() {
	return charges;
}
public void setCharges(int charges) {
	this.charges = charges;
}
public String getOfficeaddress() {
	return officeaddress;
}
public void setOfficeaddress(String officeaddress) {
	this.officeaddress = officeaddress;
}
public String getOfficephone() {
	return officephone;
}
public void setOfficephone(String officephone) {
	this.officephone = officephone;
}
@Override
public String toString() {
	return "Schedule [id=" + id + ", pid=" + pid + ", starttime=" + starttime + ", endtime=" + endtime + ", duration="
			+ duration + ", charges=" + charges + ", officeaddress=" + officeaddress + ", officephone=" + officephone
			+ "]";
}

}
