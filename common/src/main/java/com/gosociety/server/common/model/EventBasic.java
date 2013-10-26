package com.gosociety.server.common.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class EventBasic {
	
	@Id
	private String eid;
	private String place;
	private String title;
	public String mapMarkerImageUrl;
	public String calloutImageUrl;
	public String filterImageUrl;
	public Date startTime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
