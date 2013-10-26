package com.gosociety.server.services.json;

import java.util.List;

import com.gosociety.server.common.model.GoSoSceneScreenEvent;
import com.gosociety.server.common.model.GoSoSceneScreenRecommendation;

public class SocietySceneScreenJsonResponse extends BaseJsonResponse{

	public List<GoSoSceneScreenRecommendation> recommendations;
	public List<GoSoSceneScreenEvent> events;
	
	public List<GoSoSceneScreenRecommendation> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(
			List<GoSoSceneScreenRecommendation> recommendations) {
		this.recommendations = recommendations;
	}

	public List<GoSoSceneScreenEvent> getEvents() {
		return events;
	}

	public void setEvents(List<GoSoSceneScreenEvent> events) {
		this.events = events;
	}
}
