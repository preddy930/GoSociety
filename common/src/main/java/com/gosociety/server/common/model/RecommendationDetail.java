package com.gosociety.server.common.model;

import org.springframework.data.annotation.Id;

public class RecommendationDetail {
	
	@Id
	String recId;
	String title;
	String blurb;
	String detailPictureUrl;
	
	//ID of the recommender;
	String recommender;

	public String getRecId() {
		return recId;
	}
	
	public void setRecId(String recId) {
		this.recId = recId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBlurb() {
		return blurb;
	}
	
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	
	public String getDetailPictureUrl() {
		return detailPictureUrl;
	}
	
	public void setDetailPictureUrl(String detailPictureUrl) {
		this.detailPictureUrl = detailPictureUrl;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}
}
