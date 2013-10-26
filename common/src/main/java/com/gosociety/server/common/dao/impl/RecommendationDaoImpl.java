package com.gosociety.server.common.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gosociety.server.common.dao.RecommendationDao;
import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.RecommendationDetail;
import com.gosociety.server.common.model.Recommender;

public class RecommendationDaoImpl implements RecommendationDao {
	
	private MongoTemplate gosomongo;

	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}
	
	public List <RecommendationDetail> getAllRecommendations(String placeId) {
		
		Query query = new Query(Criteria.where("place").is(new ObjectId(placeId)));
		List<RecommendationDetail>recommendations = gosomongo.find(query, RecommendationDetail.class, CommonConstants.DB_TABLE_RECOMMENDATIONS);
		
		return recommendations;
	}
	
	public List <Recommender> getAllRecommenders() {
		
		List<Recommender> recommenders = gosomongo.findAll(Recommender.class, CommonConstants.DB_TABLE_RECOMMENDERS);
		
		return recommenders;
	}
	
}
