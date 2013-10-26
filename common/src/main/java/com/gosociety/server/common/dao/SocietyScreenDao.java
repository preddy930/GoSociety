package com.gosociety.server.common.dao;

import com.gosociety.server.common.model.BasicSocietyScreenData;

public interface SocietyScreenDao {

	public BasicSocietyScreenData getBasicSocietyData(String email);
}
