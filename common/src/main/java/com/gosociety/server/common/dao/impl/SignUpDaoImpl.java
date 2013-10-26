package com.gosociety.server.common.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gosociety.server.common.dao.SignUpDao;
import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.User;

@Service("signUpDao")
public class SignUpDaoImpl implements SignUpDao {
	
	private static final Logger logger = Logger.getLogger(SignUpDaoImpl.class);
	
	private MongoTemplate gosomongo;
	
	public boolean createUser(User uc) {
		
		//check to see if e-mail already exists in system
		List <User> users = gosomongo.find(new Query(Criteria.where("email").is(uc.getEmail())), User.class, CommonConstants.DB_TABLE_USERS);
		
		if (users.size() == 0) {
			gosomongo.insert(uc,CommonConstants.DB_TABLE_USERS);
			logger.debug("Added user:" + uc.getEmail() + ":to system.");
			
			return true;
		}
		
		else {
			logger.debug("Cannot create user:" + uc.getEmail() + ":already exists in database.");
			return false;
		}
	}

	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}

	@Override
	public String doesFacebookUserExist(String facebookId) {
		
		Query facebookidquery = new Query(Criteria.where("facebookId").is(facebookId));
		
		User u = gosomongo.findOne(facebookidquery, User.class, CommonConstants.DB_TABLE_USERS);
		
		if (u!=null) {
			return u.getEmail();
		}
		
		return null;
	}
}
