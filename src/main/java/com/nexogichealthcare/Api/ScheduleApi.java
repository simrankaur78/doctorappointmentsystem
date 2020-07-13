package com.nexogichealthcare.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.nexogichealthcare.Dao.Impl.ScheduleDaoImpl;
import com.nexogichealthcare.DaoInter.ScheduleDao;
import com.nexogichealthcare.models.Schedule;

public class ScheduleApi {
	Logger log=LoggerFactory.getLogger(OtpApi.class);
	final static ScheduleDao scheduledao=new ScheduleDaoImpl();
	public static int updateschedule(Schedule sc) {
		int i=scheduledao.updateschedule(sc);
		return i;
	}
	public static Schedule getScheduleByPid(int pid) {
		Schedule sc=scheduledao.getScheduleByPid(pid);
		return sc;
	}
}
