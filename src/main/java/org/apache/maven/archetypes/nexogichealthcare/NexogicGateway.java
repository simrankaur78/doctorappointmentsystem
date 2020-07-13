package org.apache.maven.archetypes.nexogichealthcare;

import static spark.Spark.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nexogichealthcare.Api.AppointmentApi;
import com.nexogichealthcare.Api.OtpApi;
import com.nexogichealthcare.Api.PractitionerApi;
import com.nexogichealthcare.Api.PractitionerEducationApi;
import com.nexogichealthcare.Api.PractitionerSkillsApi;
import com.nexogichealthcare.Api.ScheduleApi;
import com.nexogichealthcare.Dao.Impl.PractitionerDaoImpl;
import com.nexogichealthcare.DaoInter.PractitionerDao;
import com.nexogichealthcare.common.Emailer;
import com.nexogichealthcare.models.Appointment;
import com.nexogichealthcare.models.Otp;
import com.nexogichealthcare.models.Practitioner;
import com.nexogichealthcare.models.PractitionerEducation;
import com.nexogichealthcare.models.PractitionerSkill;
import com.nexogichealthcare.models.Schedule;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

/**
 * Hello world!
 *
 */
public class NexogicGateway {
	public static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

	public static Logger log = LoggerFactory.getLogger(NexogicGateway.class);

	public static void main(String[] args) {
		port(8081);
		staticFiles.location("/public");
		// redirect.get("/frontpage", "/NewFile.html");
		// get("/createprofile",(req,res)->{});
		path("/", () -> {
			get("/register", (req, res) -> {

				res.type("application/json");
				res.redirect("/PractitionerSignup.html");
				return null;
			});

			get("/nexogic", (req, res) -> {
				System.out.println("in home page");
				ArrayList<String> names = PractitionerApi.getPractitioners();
				System.out.println(names);

				HashMap<String, String> model = new HashMap<>();
				model.put("count", names.size() + "");
				for (int i = 0; i < names.size(); i++) {
					model.put("p" + i, names.get(i));
				}
				System.out.println(model);
// 				HashMap<String,String> names=new HashMap<>();
// 				names.put("88","1");
				// res.type("application/json");
				// res.redirect("/Nexogic_About.html");
				// res.body(names);
				return engine.render(new ModelAndView(model, "Nexogic_About"));

			});
			post("/bookappointment", (request, response) -> {
				System.out.println("in book appointment api");
				Appointment a=new Appointment();
				a.setPatientName(request.queryParams("patientName"));
				a.setPatientEmail(request.queryParams("patientEmail"));
				a.setPatientPhone(request.queryParams("patientPhone"));
				a.setPid(Integer.parseInt(request.queryParams("pid")));
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
				a.setDate(dateFormat.parse(request.queryParams("date")));
				System.out.println(a);
				int status=AppointmentApi.bookAppointment(a);
				HashMap<String,String> model=new HashMap<>();
				Practitioner p=PractitionerApi.getPractitionerById(Integer.parseInt(request.queryParams("pid")));
				Schedule sc=ScheduleApi.getScheduleByPid(Integer.parseInt(request.queryParams("pid")));
				model.put("pid", request.queryParams("pid"));
				model.put("name", p.getFirstname()+" "+p.getLastname());
				model.put("officeaddress", sc.getOfficeaddress());
				model.put("officephone", sc.getOfficephone());
				model.put("charges", sc.getCharges() + "");
				model.put("starttime", sc.getStarttime() + "");
				model.put("endtime", sc.getEndtime() + "");
				model.put("speciality", p.getSpeciality());
				System.out.println("model"+model);
				if(status>=1) {
					Emailer.sendEmailToPatient(request.queryParams("patientEmail"),request.queryParams("patientName"), p.getFirstname()+" "+p.getLastname(),request.queryParams("date"));
					Emailer.sendEmailToDoctor(p.getEmail(),p.getFirstname()+" "+p.getLastname(), request.queryParams("patientName"),request.queryParams("date"));
					
					model.put("status","true");
				}
				else
				model.put("status","false");
				return engine.render(new ModelAndView(model, "BookAppointment"));
				
			});
		});

//			post("/profile",(req,res)->{
//				System.out.println("method");
//				String email=re.queryParams("");
//				String regid=req.queryParams("firstname");
//				String regid=req.queryParams("lastname");
//				String regcouncil=req.queryParams("state");
//				String regyear=req.queryParams("speciality");
//				String regyear=req.queryParams("phone");
//				//System.out.println(regid+"      "+email+"      "+regcouncil+"    "+regyear);
//				//String speciality=req.queryParams("speciality");
//				//if(PractitionerApi.addmedreginpractitioner(email,regid,regcouncil,regyear)>0);
//				res.redirect("/CreateProfile.html?email=simran.bali@47billion.com");
//				return "not added";
//			});	

		path("/practitioners", () -> {
			post("/registration", (request, response) -> {
				System.out.println("in register api");

				response.type("application/json");
				final Set<String> params = request.queryParams();
				System.out.println(request.queryParams());
				final JsonObject queryParams = new JsonObject();
				// for searching
				if ((params != null) && (params.size() > 0)) {
					for (final String param : params) {

						System.out.println(param);
						System.out.println(request.queryParams(param));
						queryParams.addProperty(param, request.queryParams(param));
					}
				}

				final Practitioner practitioner = new Gson().fromJson(queryParams, Practitioner.class);
				if (PractitionerApi.registration(practitioner).equals("ERROR")) {
					response.redirect("/PractitionerSignup.html?msg=Account already exists");
				} else {// otp related code
					int otp_code = OtpApi.generateOtp();
					Otp otp = new Otp();
					otp.setOtp(otp_code);
					otp.setEmail(practitioner.getEmail());
					otp.setCreateOn(new Timestamp(new Date().getTime()));
					otp.setValidUpto(new Timestamp(new Date(System.currentTimeMillis() + 30 * 60 * 1000).getTime()));
					OtpApi.insertOtp(otp);
					Emailer.sendOtp(practitioner.getEmail(), otp.getOtp());
					// response.body(practitioner.toString());
					String Email = practitioner.getEmail();
					response.redirect("/Otp_page.html?email=" + Email);
					// if( PractitionerApi.registration(practitioner).equals(arg0);

				}
				return null;

			});
			post("/verifyotp", (request, response) -> {
				System.out.println("................" + request.queryParams("email"));
				int otp_page = Integer.valueOf(request.queryParams("otp"));
				String email = request.queryParams("email");
				System.out.println("............." + otp_page);
//					final Set<String> params = request.queryParams();
//					final JsonObject queryParams = new JsonObject();
//					// for searching
//					if ((params != null) && (params.size() > 0)) {
//					    for (final String param : params) {
//					    	
//					    	System.out.println(param);
//					    	System.out.println(request.queryParams(param));
//						queryParams.addProperty(param, request.queryParams(param));
//					    }
//					}
//					
//					final Practitioner practitioner = new Gson().fromJson(queryParams, Practitioner.class);
//			System.out.println(practitioner);
				int otp_db = OtpApi.getOtpByEmail(request.queryParams("email")).getOtp();
				System.out.println(otp_db);
				if (otp_db == otp_page) {
					PractitionerApi.setStatus(request.queryParams("email"), 1);
					response.redirect("/Login.html");
				} else { // response.header("message","invalid otp");
					response.redirect("/Otp_page.html?msg=invalid%20otp&email=" + email);
				}
				return null;
			});

			post("/resendotp", (request, response) -> {
				String email = request.queryParams("email");
				// otp related code
				int otp_code = OtpApi.generateOtp();
				Otp otp = new Otp();
				otp.setOtp(otp_code);
				otp.setEmail(request.queryParams("email"));
				otp.setCreateOn(new Timestamp(new Date().getTime()));
				otp.setValidUpto(new Timestamp(new Date(System.currentTimeMillis() + 30 * 60 * 1000).getTime()));
				OtpApi.insertOtp(otp);
				Emailer.sendOtp(email, otp.getOtp());
				response.redirect("/Otp_page.html?email=" + email);
				return "hello";
			});

			post("/login", (request, response) -> {
				String email = request.queryParams("email");
				String password = request.queryParams("password");
				if (PractitionerApi.verifyPractitioner(email, password)) {
					Practitioner practitioner = PractitionerApi.getPractitionerByEmail(email);
					HashMap<String, String> model = new HashMap<>();
					model.put("firstname", practitioner.getFirstname());
					model.put("lastname", practitioner.getLastname());
					model.put("email", practitioner.getEmail());
					model.put("speciality", practitioner.getSpeciality());
					model.put("state", practitioner.getState());
					model.put("phone", practitioner.getPhone());
					// about info
					model.put("about", practitioner.getAbout());
					// registration info
					model.put("regid", practitioner.getRegistrationId());
					model.put("regcouncil", practitioner.getRegistrationCouncil());
					model.put("regyear", practitioner.getRegistrationYear());
					model.put("pid", practitioner.getP_id() + "");
					return engine.render(new ModelAndView(model, "AboutPractitioner"));

				} else {
					response.redirect("/Login.html?msg=Invalid%20username%20or%20password");
				}
				return null;
			});

			post("/update", (request, response) -> {
				System.out.println("in update api");

//				response.type("application/json");
//				final Set<String> params = request.queryParams();
//				final JsonObject queryParams = new JsonObject();
//				// for searching
//				if ((params != null) && (params.size() > 0)) {
//					for (final String param : params) {
//
//						System.out.println(param);
//						System.out.println(request.queryParams(param));
//						queryParams.addProperty(param, request.queryParams(param));
//					}
//				}
//
//				final Practitioner practitioner = new Gson().fromJson(queryParams, Practitioner.class);
//				practitioner.setUpdatedOn(new Timestamp(new Date().getTime()));
//				int status=PractitionerApi.updatePractitioner(practitioner);
//				if(status>1)
//					return "successfull";
//				return "failed";
				// Practitioner
				// practitioner=PractitionerApi.getPractitionerByEmail(request.queryParams("pid"));
				HashMap<String, String> model = new HashMap<>();
				model.put("firstname", request.queryParams("firstname"));
				model.put("lastname", request.queryParams("lastname"));
				model.put("email", request.queryParams("email"));
				model.put("speciality", request.queryParams("speciality"));
				model.put("state", request.queryParams("state"));
				model.put("phone", request.queryParams("phone"));
				// about
				model.put("about", request.queryParams("about"));

				model.put("regid", request.queryParams("regid"));
				model.put("regcouncil", request.queryParams("regcouncil"));
				model.put("regyear", request.queryParams("regyear"));
				model.put("pid", request.queryParams("pid"));
				System.out.println(model);
				return engine.render(new ModelAndView(model, "UpdateProfile"));

			});
			post("/updateprofile", (request, response) -> {
				System.out.println("in update profile api");

				System.out.println("queryparams" + request.queryParams());
				System.out.println(request.queryParams("firstname"));
				// response.type("application/json");
				final Set<String> params = request.queryParams();
				System.out.println("set" + params);
				final JsonObject queryParams = new JsonObject();
				// for searching
				if ((params != null) && (params.size() > 0)) {
					for (final String param : params) {

						System.out.println(param);
						System.out.println(request.queryParams(param));
						queryParams.addProperty(param, request.queryParams(param));
					}
				}

				final Practitioner p = new Gson().fromJson(queryParams, Practitioner.class);

				p.setUpdatedOn(new Timestamp(new Date().getTime()));
				System.out.println(p);
				int status = PractitionerApi.updatePractitioner(p);
				if (status >= 1) {
					Practitioner practitioner = PractitionerApi.getPractitionerById(p.getP_id());
					HashMap<String, String> model = new HashMap<>();
					model.put("firstname", practitioner.getFirstname());
					model.put("lastname", practitioner.getLastname());
					model.put("email", practitioner.getEmail());
					model.put("speciality", practitioner.getSpeciality());
					model.put("state", practitioner.getState());
					model.put("phone", practitioner.getPhone());
					// about
					model.put("about", practitioner.getAbout());

					model.put("regid", practitioner.getRegistrationId());
					model.put("regcouncil", practitioner.getRegistrationCouncil());
					model.put("regyear", practitioner.getRegistrationYear());
					model.put("pid", practitioner.getP_id() + "");
					System.out.println(model);
					return engine.render(new ModelAndView(model, "UpdateProfile"));

				}
				return "some error occured";

			});
			post("/updateskills", (request, response) -> {
				System.out.println("in update skills api");

				String skills[] = request.queryParamsValues("skills");
				int p_id = Integer.parseInt(request.queryParams("p_id"));
				int skillcount = skills.length;
				PractitionerSkill ps[] = new PractitionerSkill[skillcount];
				for (int i = 0; i < skills.length; i++) {
					PractitionerSkill p = new PractitionerSkill();
					p.setPrac_id(p_id);
					p.setSkill(skills[i]);
					ps[i] = p;
				}
				int status = PractitionerSkillsApi.updateSkills(ps);
				if (status > 1) {
					Practitioner practitioner = PractitionerApi.getPractitionerById(p_id);
					
					HashMap<String, String> model = new HashMap<>();
					model.put("firstname", practitioner.getFirstname());
					model.put("lastname", practitioner.getLastname());
					model.put("email", practitioner.getEmail());
					model.put("speciality", practitioner.getSpeciality());
					model.put("state", practitioner.getState());
					model.put("phone", practitioner.getPhone());
					// about
					model.put("about", practitioner.getAbout());
//reginfo
					model.put("regid", practitioner.getRegistrationId());
					model.put("regcouncil", practitioner.getRegistrationCouncil());
					model.put("regyear", practitioner.getRegistrationYear());
					model.put("pid", practitioner.getP_id() + "");
//scheduleinfo
					
//				for(int i=0;i<skills.length;i++) {String temp=skills[i];
//				model.put("skills",skills);}
					System.out.println(model);
					return engine.render(new ModelAndView(model, "UpdateProfile"));

				}
				return "error";
			});
			post("/updateschedule", (request, response) -> {
				System.out.println("in update schedule api");
				Schedule sc = new Schedule();
				DateFormat formatter = new SimpleDateFormat("HH:mm");
				Time starttime = new Time(formatter.parse(request.queryParams("starttime")).getTime());
				Time endtime = new Time(formatter.parse(request.queryParams("endtime")).getTime());
				Time duration = new Time(formatter.parse(request.queryParams("duration")).getTime());

				sc.setOfficeaddress(request.queryParams("officeaddress"));
				sc.setPid(Integer.parseInt(request.queryParams("pid")));
				sc.setOfficephone(request.queryParams("officephone"));

				sc.setEndtime(endtime);
				sc.setStarttime(starttime);
				sc.setDuration(duration);
				sc.setCharges(Integer.parseInt(request.queryParams("charges")));

				int i = ScheduleApi.updateschedule(sc);
				if (i >= 1) {
					Practitioner practitioner = PractitionerApi
							.getPractitionerById(Integer.parseInt(request.queryParams("pid")));
					HashMap<String, String> model = new HashMap<>();
					model.put("firstname", practitioner.getFirstname());
					model.put("lastname", practitioner.getLastname());
					model.put("email", practitioner.getEmail());
					model.put("speciality", practitioner.getSpeciality());
					model.put("state", practitioner.getState());
					model.put("phone", practitioner.getPhone());
					// about
					model.put("about", practitioner.getAbout());
					// registrationinfo
					model.put("regid", practitioner.getRegistrationId());
					model.put("regcouncil", practitioner.getRegistrationCouncil());
					model.put("regyear", practitioner.getRegistrationYear());
					model.put("pid", practitioner.getP_id() + "");

					model.put("starttime", starttime + "");
					model.put("endtime", endtime + "");
					model.put("duration", duration + "");

					model.put("charges", request.queryParams("charges"));
					model.put("officeaddress", request.queryParams("officeaddress"));
					model.put("officephone", request.queryParams("officephone"));
					return engine.render(new ModelAndView(model, "UpdateProfile"));

				} else
					return "error";
			});

			post("/appointment", (request, response) -> {
				System.out.println("in appointment api");

				String name = request.queryParams("list");
				String[] arrOfStr = name.split(" ", 2);
				System.out.println(arrOfStr[0] + " " + arrOfStr[1]);
				int pid = PractitionerApi.getPractitionerIdByName(arrOfStr[0], arrOfStr[1]);
				Schedule sc = ScheduleApi.getScheduleByPid(pid);
				System.out.println(sc);
				// response.redirect("/BookAppointment.html?email=" + email);
				Practitioner p=PractitionerApi.getPractitionerById(pid);
				HashMap<String, String> model = new HashMap<>();
				model.put("pid", pid + "");
				model.put("name", name);
				model.put("officeaddress", sc.getOfficeaddress());
				model.put("officephone", sc.getOfficephone());
				model.put("charges", sc.getCharges() + "");
				model.put("starttime", sc.getStarttime() + "");
				model.put("endtime", sc.getEndtime() + "");
				model.put("speciality", p.getSpeciality());
				System.out.println("model"+model);
				return engine.render(new ModelAndView(model, "BookAppointment"));
			});

			get("/sendemailforpasswordreset", (request, response) -> {
				System.out.println("in forgot password api");
				Emailer.forgot_password(request.queryParams("email"));
				HashMap<String, String> model = new HashMap<>();
				model.put("email", request.queryParams("email"));
				return engine.render(new ModelAndView(model, "../public/Login"));
				
			});
		});

	}
}
