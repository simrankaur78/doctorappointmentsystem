package com.nexogichealthcare.Dao.Impl;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.nexogichealthcare.DaoInter.OtpDao;
import com.nexogichealthcare.Util.DbUtil;
import com.nexogichealthcare.models.Otp;

public class OtpDaoImpl implements OtpDao{
static Sql2o sql2o=DbUtil.getSql2oConnection();
static Connection con=sql2o.open();
public OtpDaoImpl() {
	super();
	sql2o.setDefaultColumnMappings(Otp.tableColumnMappings);
}
	@Override
	public int insertOtp(Otp otp) {
		int status=0;
		System.out.println(otp);
		status=con.createQuery("insert into email_otp(email,otp,create_on,valid_upto) VALUES(:email,:otp,:createOn,:validUpto)")
				.bind(otp).executeUpdate().getResult();
		
		return status;
	}

	@Override
	public Otp getOtpByEmail(String emailval) {
		System.out.println("otpbyemail");
		Otp otp=con.createQuery("select otp from email_otp where email= :email order by create_on DESC").
		addParameter("email", emailval).setColumnMappings(Otp.tableColumnMappings)
		.executeAndFetchFirst(Otp.class);
		System.out.println(otp);
		return otp;
	}

}
