package com.nexogichealthcare.Dao.Impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Query;
import com.nexogichealthcare.DaoInter.PractitionerDao;
import com.nexogichealthcare.Util.DbUtil;
import com.nexogichealthcare.Util.Sha256Util;
import com.nexogichealthcare.models.Practitioner;

public class PractitionerDaoImpl implements PractitionerDao{
	static Logger log = LoggerFactory.getLogger(PractitionerDaoImpl.class);
	final static Sql2o sql2o = DbUtil.getSql2oConnection();
	final static Connection conn = sql2o.open();
    
	
	  public PractitionerDaoImpl() { super();
	  sql2o.setDefaultColumnMappings(Practitioner.tableColumnMappings); }
//	 
	@Override
	public int insertPractitioner(Practitioner practitioner) {
		System.out.println("-----------------"+practitioner);
		int status = 0;
		try {

		        System.out.println("connection : "+sql2o);
		        
		status = conn.createQuery("insert into " +"practitioner"
				+ " (firstname"
				+ ", lastname"
				+ ", email, password, phone, speciality, state"
				+ ", createdon, updatedon"
+") "
				+ " VALUES (:firstname"
				+ ", :lastname, :email, :password, :phone, :speciality, :state"
				+ ", :createdOn, :updatedOn"
				+ ")").bind(practitioner).
				executeUpdate().getResult();
        System.out.println("status "+status);
		}catch(Exception e) {
			System.out.println("exception : "+e);
		}
		return status;
	}
	
	@Override
	public Practitioner getPractitionerByEmail(String email) {
		System.out.println("getpractionerbymailmethod");
		System.out.println(email);
		final Practitioner practitioner = conn
				.createQuery("select * from " + "practitioner" + " where email = :email")
				.addParameter("email", email).setColumnMappings(Practitioner.tableColumnMappings)
				.executeAndFetchFirst(Practitioner.class);
		return practitioner;
	}

	@Override
	public int setStatus(String email, int status) {
		final int temp = conn.createQuery("update practitioner set verify_status=:status where email=:email ")
				.addParameter("email", email).addParameter("status",status).setColumnMappings(Practitioner.tableColumnMappings)
				.executeUpdate().getResult();
		return temp;
		
	}

	@Override
	public boolean verifyPractitioner(String email, String password) {
		Practitioner practitioner;
		try {
			practitioner = conn
					.createQuery(
							"select * from " + "practitioner" + " where email = :email and password = :password")
					.addParameter("email", email)
					.addParameter("password", Sha256Util.encrypt(password))
					.setColumnMappings(Practitioner.tableColumnMappings).executeAndFetchFirst(Practitioner.class);
			return practitioner != null;
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Boolean.FALSE;
		
	}

	@Override
	public int editPractitioner(Practitioner p) {
		final int temp = conn.createQuery("update practitioner set firstname=:firstname ,lastname=:lastname "
				+ ",state=:state ,speciality=:speciality ,phone=:phone ,about=:about ,registration_id=:registration_id ,registration_council=:registration_council ,registration_year=:registration_year where email=:email ")
				.addParameter("firstname",p.getFirstname())
				.addParameter("lastname",p.getLastname())
				.addParameter("state",p.getState())
				.addParameter("speciality",p.getSpeciality())
				.addParameter("phone",p.getPhone())
				.addParameter("about",p.getAbout())
				.addParameter("registration_id",p.getRegistrationId())
				.addParameter("registration_council",p.getRegistrationCouncil())
				.addParameter("  registration_year",p.getRegistrationYear())
				.addParameter("email",p.getEmail())
				.setColumnMappings(Practitioner.tableColumnMappings)
				.executeUpdate().getResult();
		return temp;
	}
	@Override
	public String getPractitionerId(String email) {
		 Practitioner pid=conn.createQuery("select id from practitioner where email=:email").addParameter("email",email).setColumnMappings(Practitioner.tableColumnMappings).executeAndFetchFirst(Practitioner.class);
		  
		 return pid.getP_id()+"";
	}

	public int updatePractitioner(Practitioner practitioner) {
		int status = 0;
		try {
			final String sql = "update  practitioner set updatedon=:updatedOn,";
			final StringBuilder sb = new StringBuilder(sql);
			if (practitioner.getFirstname() != null) {
				sb.append("firstname=:firstname,");
			}
			if (practitioner.getLastname() != null) {
				sb.append("lastname=:lastname,");
			}
			
			if (practitioner.getPhone() != null) {
				sb.append("phone=:phone,");
			}
			
			if (practitioner.getAbout() != null) {
				sb.append("about=:about,");
			}
			if (practitioner.getSpeciality() != null) {
				sb.append("speciality=:speciality,");
			}
			if (practitioner.getState() != null) {
				sb.append("state=:state,");
			}
			if (practitioner.getRegistrationId() != null) {
				sb.append("registration_id=:registrationId,");
			}
			if (practitioner.getRegistrationCouncil() != null) {
				sb.append("registration_council=:registrationCouncil,");
			}
			if (practitioner.getRegistrationYear() != null) {
				sb.append("registration_year=:registrationYear,");
			}
			
			
			sb.deleteCharAt(sb.length() - 1);
			sb.append(" where p_id=:p_id");
			log.info(">>{}", sb.toString());
			System.out.println("query "+sb.toString());
			final Query query = conn.createQuery(sb.toString());
			query.bind(practitioner);
			status = query.executeUpdate().getResult();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	@Override
	public Practitioner getPractitionerById(int p_id) {
		 Practitioner practitioner=conn.createQuery("select * from practitioner where p_id=:p_id").addParameter("p_id",p_id).setColumnMappings(Practitioner.tableColumnMappings).executeAndFetchFirst(Practitioner.class);
		  
		 
	return practitioner;
	}
	@Override
	public ArrayList<String> getPractitioners() {
		final List<Practitioner> practitioners = conn.createQuery("select * from practitioner")
				.setColumnMappings(Practitioner.tableColumnMappings).executeAndFetch(Practitioner.class);
		ArrayList<String> names=new ArrayList<>();
		for(int i=0;i<practitioners.size();i++) {
			names.add(practitioners.get(i).getFirstname()+" "+practitioners.get(i).getLastname());
		}
		return names;
		
	}
	@Override
	public int getPractitionerIdByName(String string, String string2) {
		System.out.println("in dao"+string+" "+string2);
		Practitioner pid=conn.createQuery("select * from practitioner where firstname=:firstname and lastname=:lastname").addParameter("firstname",string).addParameter("lastname",string2).setColumnMappings(Practitioner.tableColumnMappings).executeAndFetchFirst(Practitioner.class);
		 System.out.println("in dao "+pid); 
		 return pid.getP_id();	}
	

	


}
