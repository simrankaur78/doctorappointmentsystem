package com.nexogichealthcare.Dao.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.nexogichealthcare.DaoInter.PractitionerSkillDao;
import com.nexogichealthcare.DaoInter.ScheduleDao;
import com.nexogichealthcare.Util.DbUtil;
import com.nexogichealthcare.models.Practitioner;
import com.nexogichealthcare.models.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {
	static Logger log = LoggerFactory.getLogger(PractitionerSkillDao.class);
	static Sql2o sql2o = DbUtil.getSql2oConnection();
	static Connection conn = sql2o.open();

	@Override
	public int updateschedule(Schedule sc) {
		conn.createQuery("delete from schedule where pid=:pid").addParameter("pid", sc.getPid()).executeUpdate()
				.getResult();
		//conn.createQuery("insert into schedule(officeaddress) values(:officeaddress)")
		final int temp = conn.createQuery(
				"insert into schedule(officeaddress,officephone,duration,charges,starttime,endtime,pid) values(:officeaddress ,:officephone "
						+ ",:duration ,:charges ,:starttime ,:endtime ,:pid)")
				.addParameter("officeaddress", sc.getOfficeaddress()).addParameter("officephone", sc.getOfficephone())
				.addParameter("duration", sc.getDuration()).addParameter("charges", sc.getCharges())
				.addParameter("starttime", sc.getStarttime()).addParameter("endtime", sc.getEndtime())
				.addParameter("pid", sc.getPid()).executeUpdate().getResult();
		return temp;
	}

	@Override
	public Schedule getScheduleByPid(int pid) {
		Schedule sc=conn.createQuery("select * from schedule where pid=:pid").addParameter("pid",pid).setColumnMappings(Schedule.tableColumnMappings).executeAndFetchFirst(Schedule.class);
		  
		return sc;
	}
}
