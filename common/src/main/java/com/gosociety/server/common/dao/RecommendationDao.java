package com.gosociety.server.common.dao;

import java.util.List;

import com.gosociety.server.common.model.RecommendationDetail;
import com.gosociety.server.common.model.Recommender;

public interface RecommendationDao {
	
	public List <RecommendationDetail> getAllRecommendations(String placeId);
	public List <Recommender> getAllRecommenders();
}
