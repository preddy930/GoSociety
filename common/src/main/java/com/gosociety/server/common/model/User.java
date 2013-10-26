package com.gosociety.server.common.model;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection="users")
public class User {

	private static final Logger logger = Logger.getLogger(User.class);
	
	@Id
	private String uid;
	private String email;
	private String fname;
	private String lname;
	private String password;
	private Date birthday;
	private String gender;
	private List <Society> societies = new ArrayList <Society> ();
	private List <ObjectId> checkins;
	
	private String facebookId;
	private String facebookAccessToken;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			this.birthday = sdf.parse(birthday);
		} catch (ParseException e) {
			logger.debug("Could not parse the date when creating new user - invalid format, not in form MM/dd/yyyy", e);
			throw e;
		}
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Society> getSocieties() {
		return societies;
	}

	//setting the default public societies
	public void setSocieties() {
		
		for (Society s:CommonConstants.societiesInSystem) {
			
			Society society = new Society();
			society.setSociety(s.getSociety());
			society.setLocked(s.isLocked());
			society.setSocId(s.getSocId());
			societies.add(society);
		}
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getFacebookAccessToken() {
		return facebookAccessToken;
	}
	public void setFacebookAccessToken(String facebookAccessToken) {
		this.facebookAccessToken = facebookAccessToken;
	}
	public List<ObjectId> getCheckins() {
		return checkins;
	}
	public void setCheckins(List<ObjectId> checkins) {
		this.checkins = checkins;
	}
}
