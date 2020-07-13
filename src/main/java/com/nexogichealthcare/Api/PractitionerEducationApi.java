package com.nexogichealthcare.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.nexogichealthcare.Dao.Impl.PractitionerEducationDaoImpl;

import com.nexogichealthcare.DaoInter.PractitionerEducationDao;
import com.nexogichealthcare.models.PractitionerEducation;

public class PractitionerEducationApi {
	static Logger log = LoggerFactory.getLogger(PractitionerApi.class);
	final static PractitionerEducationDao practitionereducation = new PractitionerEducationDaoImpl();

	
	public  static int updatePractitionerEducation(PractitionerEducation pe) {
		int status=practitionereducation.updateeducation(pe);
		return status;
	}

}
