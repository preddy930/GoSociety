package com.gosociety.server.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.SocietyScreenDao;
import com.gosociety.server.common.model.BasicSocietyScreenData;
import com.gosociety.server.common.model.User;
import com.gosociety.server.services.json.BaseJsonResponse;
import com.gosociety.server.services.json.SocietyScreenJsonResponse;

@Controller
@RequestMapping("/refreshsocietypage")
public class GoSoRefreshSocietyScreenController {

	private SocietyScreenDao societyScreenDao;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody 
	public SocietyScreenJsonResponse refreshSocietyScreenData(@RequestBody User u) {
		
		SocietyScreenJsonResponse response = new SocietyScreenJsonResponse();
		BasicSocietyScreenData bssd = societyScreenDao.getBasicSocietyData(u.getEmail());

		if (bssd != null) {
			response.populateResponseData(bssd);
			return response;	
		}
		
		else {
			response.setError(BaseJsonResponse.COULD_NOT_REFRESH_SOCIETY_DATA);
			return response;
		}
	}

	public SocietyScreenDao getSocietyScreenDao() {
		return societyScreenDao;
	}

	public void setSocietyScreenDao(SocietyScreenDao societyScreenDao) {
		this.societyScreenDao = societyScreenDao;
	}	
}
