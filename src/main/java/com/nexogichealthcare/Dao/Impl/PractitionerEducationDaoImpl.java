package com.nexogichealthcare.Dao.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.nexogichealthcare.DaoInter.PractitionerDao;
import com.nexogichealthcare.DaoInter.PractitionerEducationDao;
import com.nexogichealthcare.Util.DbUtil;
import com.nexogichealthcare.models.Practitioner;
import com.nexogichealthcare.models.PractitionerEducation;

public class PractitionerEducationDaoImpl implements PractitionerEducationDao{
    
	static Logger log = LoggerFactory.getLogger(PractitionerEducationDaoImpl.class);
	final static Sql2o sql2o = DbUtil.getSql2oConnection();
	final static Connection conn = sql2o.open();
    
	
	  public PractitionerEducationDaoImpl() { super();
	  sql2o.setDefaultColumnMappings(Practitioner.tableColumnMappings); }
	@Override
	public int updateeducation(PractitionerEducation pe) {
		
		return 0;
	}

}
