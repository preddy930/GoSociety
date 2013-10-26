package com.gosociety.server.common.dao;

import com.gosociety.server.common.model.User;

public interface SignUpDao {
	
	public boolean createUser(User uc);

	public String doesFacebookUserExist(String facebookId);

}
