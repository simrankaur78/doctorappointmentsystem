package com.nexogichealthcare.models;

public class PractitionerEducation {
String id;
String p_id;
String university;
String degree;
String startYear;
String endYear;
String grade;
String fieldOfStudy;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getP_id() {
	return p_id;
}
public void setP_id(String p_id) {
	this.p_id = p_id;
}
public String getUniversity() {
	return university;
}
public void setUniversity(String university) {
	this.university = university;
}
public String getDegree() {
	return degree;
}
public void setDegree(String degree) {
	this.degree = degree;
}
public String getStartYear() {
	return startYear;
}
public void setStartYear(String startYear) {
	this.startYear = startYear;
}
public String getEndYear() {
	return endYear;
}
public void setEndYear(String endYear) {
	this.endYear = endYear;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getFieldOfStudy() {
	return fieldOfStudy;
}
public void setFieldOfStudy(String fieldOfStudy) {
	this.fieldOfStudy = fieldOfStudy;
}
@Override
public String toString() {
	return "PractitionerEducation [id=" + id + ", p_id=" + p_id + ", university=" + university + ", degree=" + degree
			+ ", startYear=" + startYear + ", endYear=" + endYear + ", grade=" + grade + ", fieldOfStudy="
			+ fieldOfStudy + "]";
}



}
