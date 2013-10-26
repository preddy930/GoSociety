package com.gosociety.server.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gosociety.server.common.dao.SocietyScreenDao;
import com.gosociety.server.common.model.BasicSocietyScreenData;
import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.Society;

public class SocietyScreenDaoImpl implements SocietyScreenDao {

	private static final Logger logger = Logger.getLogger(SocietyScreenDaoImpl.class);
	
	private MongoTemplate gosomongo;
	
	public BasicSocietyScreenData getBasicSocietyData(String email) {
		
		List <Society> societies = new ArrayList<Society>();
		Query idQuery = new Query(Criteria.where("email").is(email));
		idQuery.fields().include("_id");
		idQuery.fields().include("societies");
		BasicSocietyScreenData bssd = gosomongo.findOne(idQuery, BasicSocietyScreenData.class, CommonConstants.DB_TABLE_USERS);
		
		for (Society s:bssd.getSocieties()) {
			
			for (Society socinsystem:CommonConstants.societiesInSystem) {
				
				if (s.getSociety().equals(socinsystem.getSociety()) && socinsystem.getActive().equals("true")) {
					s.setParent(socinsystem.getParent());
					s.setHierarchyValue(socinsystem.getHierarchyValue());
					s.setFilters(socinsystem.getFilters());
					s.setIconSmallUrl(socinsystem.getIconSmallUrl());
					s.setIconUrl(socinsystem.getIconUrl());
					s.setSocId(socinsystem.getSocId());
					s.setActive(socinsystem.getActive());
					societies.add(s);
				}
			}
		}
		
		bssd.setSocieties(societies);
		return bssd;
	}

	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}
}
