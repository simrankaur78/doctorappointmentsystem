package com.nexogichealthcare.Dao.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.nexogichealthcare.DaoInter.PractitionerSkillDao;
import com.nexogichealthcare.Util.DbUtil;
import com.nexogichealthcare.models.PractitionerSkill;

public class PractitionerSkillDaoImpl implements PractitionerSkillDao{
	static Logger log = LoggerFactory.getLogger(PractitionerSkillDao.class);
	final static Sql2o sql2o = DbUtil.getSql2oConnection();
	final static Connection conn = sql2o.open();
    
	
	@Override
	public int updateSkills(PractitionerSkill ps[]) {
		conn.createQuery("delete from  skills where prac_id= :prac_id ").addParameter("prac_id",ps[0].getPrac_id()).executeUpdate();
		int count=0;
		for(int i=0;i<ps.length;i++) {
		count=count+conn.createQuery("insert into skills(prac_id,skill) values(:prac_id,:skill)").
				addParameter("prac_id", ps[i].getPrac_id()).
				addParameter("skill", ps[i].getSkill()).
				executeUpdate().getResult();
		
		}return count;
	}

}
