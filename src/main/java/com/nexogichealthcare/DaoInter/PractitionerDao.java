package com.nexogichealthcare.DaoInter;

import java.util.ArrayList;
import java.util.HashMap;

import com.nexogichealthcare.models.Practitioner;

public interface PractitionerDao {
public  int insertPractitioner(Practitioner p);

public Practitioner getPractitionerByEmail(String email);

public  int setStatus(String string, int i);

public boolean verifyPractitioner(String email, String password);

public int editPractitioner(Practitioner p);

public  String getPractitionerId(String email);

public int updatePractitioner(Practitioner practitioner);

public Practitioner getPractitionerById(int p_id);

public ArrayList<String> getPractitioners();

public int getPractitionerIdByName(String string, String string2);

}

