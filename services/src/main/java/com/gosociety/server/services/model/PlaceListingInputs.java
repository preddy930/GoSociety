package com.gosociety.server.services.model;

public class PlaceListingInputs {

	private String uid;
	private String socid;
	private String searchKey;
	private String latlong;
	private int offset;
	private double distance;
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

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
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
