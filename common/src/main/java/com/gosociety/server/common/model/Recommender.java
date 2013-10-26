package com.gosociety.server.common.model;

import org.springframework.data.annotation.Id;

public class Recommender {
	
	@Id
	public String id;
	public String name;
	public String mapMarkerImageUrl;
	public String calloutImageUrl;
	public String filterImageUrl;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMapMarkerImageUrl() {
		return mapMarkerImageUrl;
	}
	
	public void setMapMarkerImageUrl(String mapMarkerImageUrl) {
		this.mapMarkerImageUrl = mapMarkerImageUrl;
	}
	
	public String getCalloutImageUrl() {
		return calloutImageUrl;
	}
	
	public void setCalloutImageUrl(String calloutImageUrl) {
		this.calloutImageUrl = calloutImageUrl;
	}
	
	public String getFilterImageUrl() {
		return filterImageUrl;
	}
	
	public void setFilterImageUrl(String filterImageUrl) {
		this.filterImageUrl = filterImageUrl;
	}
}
