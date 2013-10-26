package com.gosociety.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.PlacesDao;
import com.gosociety.server.common.model.Place;
import com.gosociety.server.services.external.bing.BingReverseGeoCodeResolver;
import com.gosociety.server.services.json.BaseJsonResponse;
import com.gosociety.server.services.json.GoSoPlaceListingResponse;
import com.gosociety.server.services.model.PlaceListingInputs;

@Controller
@RequestMapping("/placelisting")
public class GoSoPlaceListingsController {

	private PlacesDao placesDao;
	
	private BingReverseGeoCodeResolver brgcr; 
	
	@RequestMapping(value="/distance", method = RequestMethod.POST)
	@ResponseBody
	public GoSoPlaceListingResponse getPlacesByDistance(@RequestBody PlaceListingInputs input) {
		
		GoSoPlaceListingResponse response = new GoSoPlaceListingResponse();
		String searchkey = input.getSearchKey();
		int offset = input.getOffset();
		
		String [] values = input.getLatlong().split(",");
		String latitude = values[0];
		String socid = input.getSocid();
		String longitude = values[1];

		if (searchkey==null || searchkey.length()==0) {			
			searchkey = UUID.randomUUID().toString();
		}
			
		GeoResults<Place>rawPlaces = placesDao.findPlacesByDistance(searchkey, socid,latitude, longitude, offset, input.getDistance());
				
		if(rawPlaces!=null && rawPlaces.getContent().size()>0) {
			
			List<Place>places = new ArrayList<Place>();
					
			List<GeoResult<Place>> subPlaces = rawPlaces.getContent();
			
			for (GeoResult<Place> result : subPlaces) {
				Place p = result.getContent();
				places.add(p);
			}
			
			if(offset+19 > places.size()) {
				places = places.subList(offset, places.size());
			}
			
			else {
				places = places.subList(offset,offset+19);
			}
			
			response.setPlaces(places);
			response.setSearchKey(searchkey);
		}
	
		else if (rawPlaces==null) {
			
			response.setError(BaseJsonResponse.SEARCH_EXPIRED);
		}
				
		return response;
	}

	public PlacesDao getPlacesDao() {
		return placesDao;
	}

	public void setPlacesDao(PlacesDao placesDao) {
		this.placesDao = placesDao;
	}

	public BingReverseGeoCodeResolver getBrgcr() {
		return brgcr;
	}

	public void setBrgcr(BingReverseGeoCodeResolver brgcr) {
		this.brgcr = brgcr;
	}
}
