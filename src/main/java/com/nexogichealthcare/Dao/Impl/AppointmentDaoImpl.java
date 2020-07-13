package com.nexogichealthcare.Dao.Impl;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.nexogichealthcare.DaoInter.AppointmentDao;
import com.nexogichealthcare.DaoInter.ScheduleDao;
import com.nexogichealthcare.Util.DbUtil;
import com.nexogichealthcare.models.Appointment;
import com.nexogichealthcare.models.Schedule;


public class AppointmentDaoImpl implements AppointmentDao{
	static Sql2o sql2o=DbUtil.getSql2oConnection();
	static Connection con=sql2o.open();
	public AppointmentDaoImpl() {
		super();
		sql2o.setDefaultColumnMappings(Appointment.tableColumnMappings);
	}
	@Override
	public int bookAppointment(Appointment a) {
		Long bookings_done=con.createQuery("select count(*) from appointment where p_id=:p_id and date=:date").addParameter("p_id",a.getPid()).addParameter("date", a.getDate()).executeScalar(Long.class);
		System.out.println("bookings"+bookings_done);
		ScheduleDao scheduledao=new ScheduleDaoImpl();
		
        Schedule sc=scheduledao.getScheduleByPid(a.getPid());
        System.out.println(sc);
        long diff=Math.abs(sc.getEndtime().getTime()-sc.getStarttime().getTime());
        System.out.println("difference"+diff);
        System.out.println("duration"+sc.getDuration().getMinutes());
        long no_of_patients_allowed=(diff/(Math.abs(sc.getDuration().getMinutes()*60*1000)));
        System.out.println("patientd alowed"+Math.abs(no_of_patients_allowed));
        if(bookings_done<no_of_patients_allowed) {
        	System.out.println("coming");
		final int temp = con.createQuery(
				"insert into appointment(patient_name,patient_email,patient_phone,date,p_id) values(:patient_name ,:patient_email "
						+ ",:patient_phone ,:date ,:p_id)")
				.addParameter("patient_name", a.getPatientName()).addParameter("patient_email",a.getPatientEmail())
				.addParameter("patient_phone", a.getPatientPhone()).addParameter("date", a.getDate())
				.addParameter("p_id", a.getPid()).executeUpdate().getResult();
		System.out.println(temp);
		return temp;}
        else
        	return 0;
		
	}
}
