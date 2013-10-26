package com.gosociety.server.common.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.Society;

public class SocietiesDaoImpl {
	
	private static final Logger logger = Logger.getLogger(SocietiesDaoImpl.class);
	
	private MongoTemplate gosomongo;
	
	public List<Society> societies;

	//getting the list of societies (public and secret) from gosodb database.
	public void init() {
		
		societies = gosomongo.findAll(Society.class ,CommonConstants.DB_TABLE_SOCIETIES);
		
		for (Society s : societies) {
			CommonConstants.societiesInSystem.add(s);
			logger.debug("Retrieved society:"+s.getSociety());
		}
	}
	
	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}
}
