package com.nexogichealthcare.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexogichealthcare.Dao.Impl.AppointmentDaoImpl;
import com.nexogichealthcare.DaoInter.AppointmentDao;
import com.nexogichealthcare.models.Appointment;



public class AppointmentApi {
	Logger log=LoggerFactory.getLogger(AppointmentApi.class);
	final static AppointmentDao appointmentdao=new AppointmentDaoImpl();
	public static int bookAppointment(Appointment a) {
	int i=appointmentdao.bookAppointment(a);
	return i;
	}
}
