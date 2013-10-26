package com.gosociety.server.common.dao;


public interface LoginDao {

	public boolean validateUser(String email,String password);
}
