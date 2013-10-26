package com.gosociety.server.common.dao;

import org.bson.types.ObjectId;

import com.gosociety.server.common.model.CheckIn;

public interface CheckInDao {

	void recordCheckIn(String pid, String uid, String note, long checkintime, int uniquenessValue);

	CheckIn getCheckInDetails(ObjectId usercheckin);
}
