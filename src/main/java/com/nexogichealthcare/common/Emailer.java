package com.nexogichealthcare.common;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nexogichealthcare.Api.OtpApi;
import com.nexogichealthcare.Dao.Impl.OtpDaoImpl;
import com.nexogichealthcare.DaoInter.OtpDao;
import com.nexogichealthcare.Util.PropertyLoader;
import com.nexogichealthcare.models.Otp;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;;


public class Emailer {
	static Logger log = LoggerFactory.getLogger(Emailer.class);
	static Properties props = PropertyLoader.loadProperties();

	final static OtpDao otpDao = new OtpDaoImpl();
	
	public static String sendOtp(String emailRecipient, int otp) throws IOException {

//		final SendGrid sg = new SendGrid(props.getProperty("SENDGRID_API_KEY"));
//		final String otpTemplateId = props.getProperty("SENDGRID_OTP_TEMPLATE_ID");
//	    final String senderEmail = props.getProperty("SENDGRID_SENDER_EMAIL");
//		
//		
		final SendGrid sg = new SendGrid("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");
		final String otpTemplateId = "d-5fe07c0109da48689ca81705a8dcf1e1";
		final String senderEmail = "simranbali78@gmail.com";
       System.out.println("in endotp");
       System.out.println(props.getProperty("SENDGRID_SENDER_EMAIL"));
       System.out.println(emailRecipient);
       System.out.println(otp);
       System.out.println(props.getProperty("SENDGRID_API_KEY"));
       System.out.println(props.getProperty("SENDGRID_OTP_TEMPLATE_ID"));
		Request request = new Request();
		try {
			
			
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody("{\"from\":{\"email\":\"" + senderEmail + "\"},"
					+ "\"personalizations\":[{\"to\":[{\"email\":\"" + emailRecipient + "\"}],"
					+ "\"dynamic_template_data\":{\"VERIFICATION_CODE\":\"" +otp  + "\"}}],"
					+ "\"template_id\":\"" + otpTemplateId + "\"" + "}");
			sg.api(request);
			return "success";
		} catch (final IOException ex) {
			throw ex;
		}
	}

//	public static String sendEmailForForgotPassword(String emailRecipient, String type) throws Exception {
//		final SendGrid sg = new SendGrid(props.getProperty("SENDGRID_API_KEY"));
//		final String otpTemplateId = props.getProperty("FORGOT_PASSWORD_TEMPLATE_ID");
//		final String senderEmail = props.getProperty("SENDGRID_SENDER_EMAIL");
//		final Request request = new Request();
//		final String jwt = JwTokenHelper.getInstance().generatePrivateKey("Token Details", "", emailRecipient, 0, type,
//				"");
//
//		final String url = props.getProperty("FORGOT_PASSWORD_URL") + jwt;
//		try {
//			request.setMethod(Method.POST);
//			request.setEndpoint("mail/send");
//			request.setBody(
//					"{\"from\":{\"email\":\"" + senderEmail + "\"}," + "\"personalizations\":[{\"to\":[{\"email\":\""
//							+ emailRecipient + "\"}]," + "\"dynamic_template_data\":{\"forgot-password-link\":\"" + url
//							+ "\"}}]," + "\"template_id\":\"" + otpTemplateId + "\"" + "}");
//			sg.api(request);
//			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Email sent to " + emailRecipient));
//		} catch (final IOException ex) {
//			throw ex;
//		}
//	}

	public static String getOtp(String email) {
		final Otp otpDetails = otpDao.getOtpByEmail(email);
		final JsonObject obj = new JsonObject();
		obj.add("otp", new Gson().toJsonTree(otpDetails));
		return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "", obj));
	}

	public static String sendEmailToPatient(String emailRecipient, String patientname, String doctorname, String date) throws IOException{
		final SendGrid sg = new SendGrid("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");
		final String emailtopatienttemplateid = "d-11548bedc19a4b748d84f5e01cfa2398";
		final String senderEmail = "simranbali78@gmail.com";
       System.out.println("in patientemail");
       System.out.println(props.getProperty("SENDGRID_SENDER_EMAIL"));
       System.out.println(emailRecipient);
       System.out.println(patientname);
       System.out.println(doctorname);
       System.out.println(date);
       
		Request request = new Request();
		try {
			
			
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody("{\"from\":{\"email\":\"" + senderEmail + "\"},"
					+ "\"personalizations\":[{\"to\":[{\"email\":\"" + emailRecipient + "\"}],"
					+ "\"dynamic_template_data\":{\"PATIENT_NAME\":\"" +patientname  + "\" ,\"DOCTOR_NAME\":\"" +doctorname  + "\" ,\"DATE\":\"" +date  + "\" }}],"
					+ "\"template_id\":\"" + emailtopatienttemplateid + "\"" + "}");
			sg.api(request);
			return "success";
		} catch (final IOException ex) {
			throw ex;
		}
		
	}

	public static String sendEmailToDoctor(String emailRecipient, String doctorname, String patientname, String date) throws IOException {
		final SendGrid sg = new SendGrid("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");
		final String emailtodoctortemplateid = "d-e7fe087c91124fb7a5e93f7826e57ea0"; 
		final String senderEmail = "simranbali78@gmail.com";
       System.out.println("in doctor email");
       System.out.println(props.getProperty("SENDGRID_SENDER_EMAIL"));
       System.out.println(emailRecipient);
       System.out.println(patientname);
       System.out.println(doctorname);
       System.out.println(date);
       Request request = new Request();
		try {
			
			
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody("{\"from\":{\"email\":\"" + senderEmail + "\"},"
					+ "\"personalizations\":[{\"to\":[{\"email\":\"" + emailRecipient + "\"}],"
					+ "\"dynamic_template_data\":{\"DOCTOR_NAME\":\"" +doctorname  + "\" ,\"PATIENT_NAME\":\"" +patientname  + "\" ,\"DATE\":\"" +date  + "\" }}],"
					+ "\"template_id\":\"" + emailtodoctortemplateid + "\"" + "}");
			sg.api(request);
			return "success";
		} catch (final IOException ex) {
			throw ex;
		}
	
		
		
	}

	public static String forgot_password(String queryParams) {
		// TODO Auto-generated method stub
		return"success";
	}

}
