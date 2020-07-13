package com.nexogichealthcare.models;

import java.util.HashMap;
import java.util.Map;

public class PractitionerSkill {
int id;
int prac_id;
String skill;

public static Map<String, String> tableColumnMappings = new HashMap<>(); 
static {
    tableColumnMappings.put("id", "id");
    tableColumnMappings.put("prac_id", "prac_id");
    tableColumnMappings.put("skill", "skill");
    
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}
public int getPrac_id() {
	return prac_id;
}
public void setPrac_id(int prac_id) {
	this.prac_id = prac_id;
}
@Override
public String toString() {
	return "PractitionerSkill [id=" + id + ", prac_id=" + prac_id + ", skill=" + skill + "]";
}


}
