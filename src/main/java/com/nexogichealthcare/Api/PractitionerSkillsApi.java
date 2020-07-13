package com.nexogichealthcare.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.nexogichealthcare.Dao.Impl.PractitionerSkillDaoImpl;

import com.nexogichealthcare.DaoInter.PractitionerSkillDao;
import com.nexogichealthcare.models.PractitionerSkill;

public class PractitionerSkillsApi {
	static Logger log = LoggerFactory.getLogger(PractitionerApi.class);
	final static PractitionerSkillDao practitionerSkillDao = new PractitionerSkillDaoImpl();

	public static int updateSkills(PractitionerSkill []ps) {
		int status=practitionerSkillDao.updateSkills(ps);
		return status;
	}
		
}
