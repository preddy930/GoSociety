package com.gosociety.server.common.model;

public class GoSoSceneScreenRecommendation {
	
	Place place;
	RecommendationBasic recommendation;
	Recommender recommender;
	
	public Place getPlace() {
		return place;
	}
	
	public void setPlace(Place place) {
		this.place = place;
	}
	
	public RecommendationBasic getRecommendation() {
		return recommendation;
	}
	
	public void setRecommendation(RecommendationBasic recommendation) {
		this.recommendation = recommendation;
	}
	
	public Recommender getRecommender() {
		return recommender;
	}
	
	public void setRecommender(Recommender recommender) {
		this.recommender = recommender;
	}
}
