package com.gosociety.server.common.dao;

import org.springframework.data.mongodb.core.geo.GeoResults;

import com.gosociety.server.common.model.Place;

public interface PlacesDao {

	public GeoResults<Place> findPlacesByDistance(String searchKey, String socid, String latitude, String longitude, int offset, double distance);
	
	public Place findPlaceById(String pid);
}
