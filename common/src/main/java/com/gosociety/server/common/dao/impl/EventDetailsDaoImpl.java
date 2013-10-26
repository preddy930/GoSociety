package com.gosociety.server.common.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gosociety.server.common.dao.EventDetailsDao;
import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.EventDetail;

public class EventDetailsDaoImpl implements EventDetailsDao {
	
	private MongoTemplate gosomongo;
	
	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}

	public EventDetail getEventDetails (String eventId) {
		
		Query query = new Query(Criteria.where("_id").is(new ObjectId(eventId)));
		
		EventDetail e= gosomongo.findOne(query, EventDetail.class, CommonConstants.DB_TABLE_EVENTS);
		
		return e;
	}
}
