package com.gosociety.server.services.model;

public class BasicGeoLocationInputs {
	
	String uid;
	String socid;
	String latlong;
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getSocid() {
		return socid;
	}
	
	public void setSocid(String socid) {
		this.socid = socid;
	}
	
	public String getLatlong() {
		return latlong;
	}
	
	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}
}
