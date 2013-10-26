package com.gosociety.server.common.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.gosociety.server.common.dao.UserDao;
import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.User;
import com.mongodb.WriteResult;

public class UserDaoImpl implements UserDao {

	private MongoTemplate gosomongo;
	
	@Override
	public User getUser(String uid) {
		
		Query query = new Query(Criteria.where("_id").is(new ObjectId(uid)));	
		User u = gosomongo.findOne(query, User.class, CommonConstants.DB_TABLE_USERS);
		
		return u;
	}

	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}

	@Override
	public String updateUserFBAccessToken(String uid, String token) {
		
		Query query = new Query(Criteria.where("_id").is(new ObjectId(uid)));
		WriteResult wr = gosomongo.updateFirst(query, Update.update("facebookAccessToken",token), CommonConstants.DB_TABLE_USERS);
		return wr.getError();
	}
}
