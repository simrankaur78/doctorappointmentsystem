package com.nexogichealthcare.Api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.nexogichealthcare.Dao.Impl.PractitionerDaoImpl;
import com.nexogichealthcare.DaoInter.PractitionerDao;
import com.nexogichealthcare.Util.Sha256Util;
import com.nexogichealthcare.common.StandardResponse;
import com.nexogichealthcare.common.StatusResponse;
import com.nexogichealthcare.models.Practitioner;
import com.nexogichealthcare.models.Schedule;

public class PractitionerApi {
	static Logger log = LoggerFactory.getLogger(PractitionerApi.class);
	final static PractitionerDao practitionerDao = new PractitionerDaoImpl();

	
		
	public static String registration(Practitioner practitioner) {
		System.out.println("registration"+practitioner);
		String message = "";
		StatusResponse responseType = StatusResponse.ERROR;
		try {

			if (practitioner.getEmail().isEmpty()) {
				message = "Email can not be empty !";
			} else {
				if (practitionerDao.getPractitionerByEmail(practitioner.getEmail()) != null) {
					
					message = "Email already exist !";
				} else {
					if (practitioner.getPassword() != null) {
						practitioner.setPassword(Sha256Util.encrypt(practitioner.getPassword()));
						System.out.println(Sha256Util.encrypt(practitioner.getPassword()));
					}
					practitioner.setCreatedOn(new Timestamp(new Date().getTime()));
					practitioner.setUpdatedOn(new Timestamp(new Date().getTime()));
					practitioner.setVerifyStatus(0);
					System.out.println("sab aara hai kya"+practitioner);
					final int status = practitionerDao.insertPractitioner(practitioner);
					if (status <= 0) {
						log.info("some error has occured while saving data to database");
				}else
					responseType=StatusResponse.SUCCESS;
			}
		}} catch (final Exception e) {
			e.printStackTrace();
			log.info("Error in registartion of pratitioner >> {}", e.getMessage());
			message = "Some error occured, please try again after sometime.";
		}
		return responseType.toString();
	}



	public static void setStatus(String string, int i) {
	  
		practitionerDao.setStatus(string,i);
	}



	public static boolean verifyPractitioner(String email, String password) {
		boolean b=practitionerDao.verifyPractitioner(email,password);
		return b;
		
	}
	public static Practitioner getPractitionerByEmail(String email) {
		Practitioner p=practitionerDao.getPractitionerByEmail(email);
		return p;
	}



	public static int editPractitioner(Practitioner p) {
		int status=practitionerDao.editPractitioner(p);
		return status;
	}











	public static String getPractitionerId(String email) {
		String pid=practitionerDao.getPractitionerId(email);
		return pid;
	}


//updatePractitioner
	 public static int updatePractitioner(Practitioner practitioner) {
		int status=practitionerDao.updatePractitioner(practitioner);
		return status;
	}



	public static Practitioner getPractitionerById(int p_id) {
		// TODO Auto-generated method stub
		Practitioner practitioner=practitionerDao.getPractitionerById(p_id);
		return practitioner;
	}

public static ArrayList<String> getPractitioners(){
	ArrayList<String> list=practitionerDao.getPractitioners();
	return list;
}



public static int getPractitionerIdByName(String string, String string2) {
	int pid=practitionerDao.getPractitionerIdByName(string,string2);
	return pid;
}





}
