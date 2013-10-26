package com.gosociety.server.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.UserDao;
import com.gosociety.server.common.model.User;
import com.gosociety.server.services.json.BaseJsonResponse;

@Controller
@RequestMapping("/userupdate")
public class GoSoUserUpdateController {
	
	private UserDao userDao;
	
	@RequestMapping(value="/fbtoken", method=RequestMethod.POST)
	@ResponseBody
	public BaseJsonResponse updateFacebookToken(@RequestBody User uc) {
		
		BaseJsonResponse response = new BaseJsonResponse();
		String error = userDao.updateUserFBAccessToken(uc.getUid(), uc.getFacebookAccessToken());
		
		if (error!=null) {
			String [] values = {error};
			response.setError(BaseJsonResponse.COULD_NOT_UPDATE_FBTOKEN, values);
		}
		
		return response;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
