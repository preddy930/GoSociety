package com.gosociety.server.common.dao.impl;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.gosociety.server.common.dao.CheckInDao;
import com.gosociety.server.common.dao.UserDao;
import com.gosociety.server.common.model.CheckIn;
import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.User;
import com.mongodb.WriteResult;

public class CheckInDaoImpl implements CheckInDao {

	private MongoTemplate gosomongo;
	private UserDao userDao;
	
	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}
	
	@Override
	public void recordCheckIn(String pid, String uid, String note, long checkintime, int uniquenessValue) {
		
		CheckIn checkin = new CheckIn(new ObjectId(pid),new ObjectId(uid),note,new Date(checkintime), uniquenessValue);
		gosomongo.insert(checkin,CommonConstants.DB_TABLE_CHECKINS);
		
		Query query = new Query(Criteria.where("pid").is(new ObjectId(pid))).addCriteria(Criteria.where("uid").is(new ObjectId(uid)))
			.addCriteria(Criteria.where("checkintime").is(new Date(checkintime)));
		
		checkin = gosomongo.findOne(query, CheckIn.class, CommonConstants.DB_TABLE_CHECKINS);
		Update update = new Update().push("checkins", checkin.getId());
		
		//update the place collection with the checkin id
		Query query2 = new Query(Criteria.where("_id").is(new ObjectId(pid)));
		WriteResult place = gosomongo.updateFirst(query2, update, CommonConstants.DB_TABLE_PLACES);
		place.getLastError();
		
		//update the user collection with the checkin id
		query2 = new Query(Criteria.where("_id").is(new ObjectId(uid)));
		WriteResult user = gosomongo.updateFirst(query2, update, CommonConstants.DB_TABLE_USERS);
		user.getLastError();
	}
	
	public CheckIn getCheckInDetails(ObjectId usercheckin) {
		
		Query query = new Query(Criteria.where("_id").is(usercheckin));
		return gosomongo.findOne(query, CheckIn.class, CommonConstants.DB_TABLE_CHECKINS);
	}
}
