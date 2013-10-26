package com.gosociety.server.services.model;

public class GoSoCheckInControllerInputs {
	
	String uid;
	String pid;
	String note;
	String latlong;
	boolean facebookShare;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isFacebookShare() {
		return facebookShare;
	}

	public void setFacebookShare(boolean facebookShare) {
		this.facebookShare = facebookShare;
	}

	public String getLatlong() {
		return latlong;
	}

	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}
}