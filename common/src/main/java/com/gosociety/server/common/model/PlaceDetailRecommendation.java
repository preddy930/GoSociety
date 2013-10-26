package com.gosociety.server.common.model;

public class PlaceDetailRecommendation {
	
	private RecommendationDetail recommendation;
	private Recommender recommender = new Recommender();
	
	public RecommendationDetail getRecommendation() {
		return recommendation;
	}
	
	public void setRecommendation(RecommendationDetail recommendation) {
		this.recommendation = recommendation;
	}
	
	public Recommender getRecommender() {
		return recommender;
	}
	
	public void setRecommender(Recommender recommender) {
		this.recommender = recommender;
	}
}
