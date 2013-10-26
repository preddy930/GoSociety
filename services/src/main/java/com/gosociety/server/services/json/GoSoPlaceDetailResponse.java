package com.gosociety.server.services.json;

import java.util.List;

import com.gosociety.server.common.model.PlaceDetailRecommendation;

public class GoSoPlaceDetailResponse extends BaseJsonResponse{	
	
	List<PlaceDetailRecommendation>recommendations;

	public List<PlaceDetailRecommendation> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<PlaceDetailRecommendation> recommendations) {
		this.recommendations = recommendations;
	}
}