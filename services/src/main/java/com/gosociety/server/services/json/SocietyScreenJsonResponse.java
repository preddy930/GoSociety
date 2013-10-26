package com.gosociety.server.services.json;

import java.util.List;

import com.gosociety.server.common.model.BasicSocietyScreenData;
import com.gosociety.server.common.model.Society;

public class SocietyScreenJsonResponse extends BaseJsonResponse {

	private String userId;
	private List<Society> usersocieties;

	public List<Society> getUsersocieties() {
		return usersocieties;
	}

	public void setUsersocieties(List<Society> usersocieties) {
		this.usersocieties = usersocieties;
	}

	public void populateResponseData(BasicSocietyScreenData bssd) {
		this.setUserId(bssd.get_id());
		this.setUsersocieties(bssd.getSocieties());	
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}