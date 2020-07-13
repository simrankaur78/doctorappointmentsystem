package com.nexogichealthcare.DaoInter;

import com.nexogichealthcare.models.Schedule;

public interface ScheduleDao {

	int updateschedule(Schedule sc);

	Schedule getScheduleByPid(int pid);

}
