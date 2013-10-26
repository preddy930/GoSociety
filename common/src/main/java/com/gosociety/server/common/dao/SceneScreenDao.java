package com.gosociety.server.common.dao;

import java.util.List;

import com.gosociety.server.common.model.GoSoSceneScreenEvent;
import com.gosociety.server.common.model.GoSoSceneScreenRecommendation;

public interface SceneScreenDao {
	
	List<GoSoSceneScreenRecommendation> getRecommendations(String societyId, String city, String state);
	List<GoSoSceneScreenEvent> getEvents(String societyId, String city, String state);
}
