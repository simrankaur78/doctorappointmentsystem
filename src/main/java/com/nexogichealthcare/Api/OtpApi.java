package com.nexogichealthcare.Api;

import java.sql.Timestamp;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexogichealthcare.Dao.Impl.OtpDaoImpl;
import com.nexogichealthcare.DaoInter.OtpDao;
import com.nexogichealthcare.models.Otp;

public class OtpApi {
	Logger log=LoggerFactory.getLogger(OtpApi.class);
	final static OtpDao otpdao=new OtpDaoImpl();
	 public static Integer generateOtp()
	   {
	       Random randomNumber = new Random();
	       int result = 0;
	       for(int i = 0; i < 4; i++) {
	           result = result * 10 + (randomNumber.nextInt(9) + 1);
	       }
	       return result;
	   }
	 public static String insertOtp(Otp otp) {
		otpdao.insertOtp(otp);
		 return "";
	 }
	 
	 public static Otp getOtpByEmail(String email) {
		 Otp otp=otpdao.getOtpByEmail(email);
		 return otp;
	 }
	 
	 
}
