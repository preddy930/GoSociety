package com.gosociety.server.common.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import com.gosociety.server.common.dao.PlacesDao;
import com.gosociety.server.common.model.CommonConstants;
import com.gosociety.server.common.model.Place;

public class PlacesDaoImpl implements PlacesDao {
	
	private MongoTemplate gosomongo;
	
	public MongoTemplate getGosomongo() {
		return gosomongo;
	}

	public void setGosomongo(MongoTemplate gosomongo) {
		this.gosomongo = gosomongo;
	}

	public GeoResults<Place> findPlacesByDistance(String searchKey, String socid, String latitude,String longitude, int offset,double distance) {
		
		Point location = new Point(Double.valueOf(latitude), Double.valueOf(longitude));
		
		Query filter = new Query(Criteria.where("societies").is(new ObjectId(socid)));
		
		//need to convert miles to degrees, thus divide by 69
		NearQuery query = NearQuery.near(location).maxDistance(distance/69).num(4000).distanceMultiplier(69).query(filter);
		
		GeoResults<Place> places = gosomongo.geoNear(query, Place.class);
		
		return places;
	}
	
	public Place findPlaceById(String pid) {
		
		Query query = new Query(Criteria.where("_id").is(new ObjectId(pid)));
		Place p = gosomongo.findOne(query, Place.class, CommonConstants.DB_TABLE_PLACES);
		
		return p;
	}
}