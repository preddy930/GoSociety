package com.gosociety.server.common.dao.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gosociety.server.common.dao.LoginDao;
import com.gosociety.server.common.model.BasicSocietyScreenData;
import com.gosociety.server.common.model.CommonConstants;


public class LoginDaoImpl implements LoginDao {

	private MongoTemplate gosomongo;
	
	public int getSocities() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validateUser(String email,String password) {
		
		Query idQuery = new Query(Criteria.where("email").is(email)).addCriteria(Criteria.where("password").is(password));
		if (gosomongo.findOne(idQuery, BasicSocietyScreenData.class, CommonConstants.DB_TABLE_USERS)!=null) {
			return true;
		}
		
		return false;
	}

	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}
}
