package com.gosociety.server.common.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class CheckIn {

	@Id
	ObjectId id;
	ObjectId pid;
	ObjectId uid;
	String note;
	Date checkintime;
	int uniquenessValue;
	
	public CheckIn(ObjectId pid, ObjectId uid, String note, Date checkintime,int uniquenessValue) {
		
		this.pid = pid;
		this.uid = uid;
		this.note = note;
		this.checkintime = checkintime;
		this.uniquenessValue = uniquenessValue;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public ObjectId getPid() {
		return pid;
	}

	public void setPid(ObjectId pid) {
		this.pid = pid;
	}

	public ObjectId getUid() {
		return uid;
	}

	public void setUid(ObjectId uid) {
		this.uid = uid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCheckintime() {
		return checkintime;
	}

	public void setCheckintime(Date checkintime) {
		this.checkintime = checkintime;
	}

	public int getUniquenessValue() {
		return uniquenessValue;
	}

	public void setUniquenessValue(int uniquenessValue) {
		this.uniquenessValue = uniquenessValue;
	}

}
