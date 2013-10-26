package com.gosociety.server.common.dao;

import com.gosociety.server.common.model.User;

public interface UserDao {
	
	public User getUser(String uid);
	
	public String updateUserFBAccessToken(String uid, String token);

}
