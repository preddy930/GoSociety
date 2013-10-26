package com.gosociety.server.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gosociety.server.common.dao.SceneScreenDao;
import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.EventBasic;
import com.gosociety.server.common.model.GoSoSceneScreenEvent;
import com.gosociety.server.common.model.GoSoSceneScreenRecommendation;
import com.gosociety.server.common.model.Place;
import com.gosociety.server.common.model.RecommendationBasic;
import com.gosociety.server.common.model.Recommender;
import com.gosociety.server.common.model.Society;

public class SceneScreenDaoImpl implements SceneScreenDao {

	private MongoTemplate gosomongo;
	
	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}

	@Override
	public List<GoSoSceneScreenRecommendation> getRecommendations(String societyId, String city, String state) {
		//query recommendations, filter on city, state, and society
		
		Query query = new Query(Criteria.where("city").is(city)).addCriteria(Criteria.where("state").is(state))
			.addCriteria(Criteria.where("society").is(new ObjectId(societyId)));
		
		List<RecommendationBasic>recommendations = gosomongo.find(query, RecommendationBasic.class, CommonConstants.DB_TABLE_RECOMMENDATIONS);
		
		List<GoSoSceneScreenRecommendation> gssr=null;
		if (recommendations!=null || recommendations.size() > 0) {
		
			gssr = new ArrayList<GoSoSceneScreenRecommendation>();
		
			for (RecommendationBasic r:recommendations) {
				GoSoSceneScreenRecommendation rec = new GoSoSceneScreenRecommendation();
				rec.setRecommendation(r);
			
				query = new Query(Criteria.where("_id").is(new ObjectId(r.getPlace())));
				Place p = gosomongo.findOne(query, Place.class, CommonConstants.DB_TABLE_PLACES);
				r.setPlace("");			
				rec.setPlace(p);

				query = new Query(Criteria.where("_id").is(new ObjectId(r.getRecommender())));
				Recommender recommender = gosomongo.findOne(query, Recommender.class, CommonConstants.DB_TABLE_RECOMMENDERS);
				r.setRecommender("");
				rec.setRecommender(recommender);
			
				gssr.add(rec);
			}
		}
		
		return gssr;
	}

	@Override
	public List<GoSoSceneScreenEvent> getEvents(String societyId,String city, String state) {
		
		Query query = new Query(Criteria.where("city").is(city)).addCriteria(Criteria.where("state").is(state))
			.addCriteria(Criteria.where("society").is(new ObjectId(societyId)));
		
		List<EventBasic> events = gosomongo.find(query, EventBasic.class, CommonConstants.DB_TABLE_EVENTS);
		
		List<GoSoSceneScreenEvent> gsse=null;
		if (events!=null || events.size() > 0) {
			
			gsse = new ArrayList<GoSoSceneScreenEvent>();
		
			for (EventBasic e:events) {
				GoSoSceneScreenEvent evt = new GoSoSceneScreenEvent();
				evt.setEvent(e);
				
				query = new Query(Criteria.where("_id").is(new ObjectId(e.getPlace())));
				e.setPlace("");
				Place p = gosomongo.findOne(query, Place.class, CommonConstants.DB_TABLE_PLACES);
				evt.setPlace(p);
				
				gsse.add(evt);
			}
		}
		
		return gsse;
	}
}