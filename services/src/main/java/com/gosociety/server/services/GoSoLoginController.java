package com.gosociety.server.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.LoginDao;
import com.gosociety.server.common.dao.SocietyScreenDao;
import com.gosociety.server.common.model.BasicSocietyScreenData;
import com.gosociety.server.common.model.User;
import com.gosociety.server.services.json.BaseJsonResponse;
import com.gosociety.server.services.json.SocietyScreenJsonResponse;

@Controller
@RequestMapping("/login")
public class GoSoLoginController {
	
	private LoginDao loginDao;
	private SocietyScreenDao societyScreenDao;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public SocietyScreenJsonResponse validateUser(@RequestBody User u) {				
		
		SocietyScreenJsonResponse response = new SocietyScreenJsonResponse();
		
		if (loginDao.validateUser(u.getEmail(), u.getPassword())) {
		
			BasicSocietyScreenData bssd = societyScreenDao.getBasicSocietyData(u.getEmail());
			
			response.populateResponseData(bssd);
			return response;
		}
		
		else {
			response.setError(BaseJsonResponse.INVALID_CREDS);
			return response;
		}
	}
		
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public SocietyScreenDao getSocietyScreenDao() {
		return societyScreenDao;
	}

	public void setSocietyScreenDao(SocietyScreenDao societyScreenDao) {
		this.societyScreenDao = societyScreenDao;
	}
}
