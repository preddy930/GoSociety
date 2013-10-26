package com.gosociety.server.services.json;

import java.util.List;
import com.gosociety.server.common.model.Place;

public class GoSoPlaceListingResponse extends BaseJsonResponse {
	
	List<Place>places;
	private String searchKey;

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
