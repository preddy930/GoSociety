package com.gosociety.server.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.SignUpDao;
import com.gosociety.server.common.dao.SocietyScreenDao;
import com.gosociety.server.common.model.BasicSocietyScreenData;
import com.gosociety.server.common.model.User;
import com.gosociety.server.services.json.BaseJsonResponse;
import com.gosociety.server.services.json.SocietyScreenJsonResponse;


@Controller
@RequestMapping("/signup")
public class GoSoSignUpController {

	private static final Logger logger = Logger.getLogger(GoSoSignUpController.class);
	
	private SignUpDao signUpDao;
	private SocietyScreenDao societyScreenDao;
	
	@RequestMapping(value="/viafacebook", method=RequestMethod.POST)
	@ResponseBody
	public BaseJsonResponse verifyFacebookUser(@RequestBody User uc) {
		
		BaseJsonResponse response = new BaseJsonResponse();
		
		String emailAddress=signUpDao.doesFacebookUserExist(uc.getFacebookId());
		
		String [] values = {emailAddress};
		
		if (emailAddress!=null) {
			response.setError(BaseJsonResponse.FACEBOOK_ID_EXISTS_IN_SYSTEM, values);
			
			return response;
		}
		
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
    public BaseJsonResponse createUser(@RequestBody User uc) {
		
		SocietyScreenJsonResponse response = new SocietyScreenJsonResponse();

		uc.setSocieties();
		
		boolean create = signUpDao.createUser(uc);
		
		if (!create) {
			logger.debug("Could not create the user:"+uc.getEmail()+":already exists in database");
			response.setError(BaseJsonResponse.USER_EXISTS);
			return response;
		}
		
		BasicSocietyScreenData bssd = societyScreenDao.getBasicSocietyData(uc.getEmail());
		response.populateResponseData(bssd);
		
		return response;
	}

	public SignUpDao getSignUpDao() {
		return signUpDao;
	}

	public void setSignUpDao(SignUpDao signUpDao) {
		this.signUpDao = signUpDao;
	}

	public SocietyScreenDao getSocietyScreenDao() {
		return societyScreenDao;
	}

	public void setSocietyScreenDao(SocietyScreenDao societyScreenDao) {
		this.societyScreenDao = societyScreenDao;
	}
}
