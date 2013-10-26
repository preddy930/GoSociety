package com.gosociety.server.common.model;

import org.springframework.data.annotation.Id;

public class EventDetail {
	
	@Id
	private String eid;
	
	private String detailImageUrl;
	private float reservationPrice;
	private float eventPrice;
	private float waitlistPrice;
	private String blurb;
	
	int spotsAvailable;
	int spotsRemaining;
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getDetailImageUrl() {
		return detailImageUrl;
	}
	public void setDetailImageUrl(String detailImageUrl) {
		this.detailImageUrl = detailImageUrl;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	public int getSpotsAvailable() {
		return spotsAvailable;
	}
	public void setSpotsAvailable(int spotsAvailable) {
		this.spotsAvailable = spotsAvailable;
	}
	public int getSpotsRemaining() {
		return spotsRemaining;
	}
	public void setSpotsRemaining(int spotsRemaining) {
		this.spotsRemaining = spotsRemaining;
	}
	public void setReservationPrice(float reservationPrice) {
		this.reservationPrice = reservationPrice;
	}
	public void setEventPrice(float eventPrice) {
		this.eventPrice = eventPrice;
	}
	public void setWaitlistPrice(float waitlistPrice) {
		this.waitlistPrice = waitlistPrice;
	}
	public float getReservationPrice() {
		return reservationPrice;
	}
	public float getEventPrice() {
		return eventPrice;
	}
	public float getWaitlistPrice() {
		return waitlistPrice;
	}
}
