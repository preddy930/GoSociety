package com.gosociety.server.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.SceneScreenDao;
import com.gosociety.server.common.exception.GoSoHttpClientException;
import com.gosociety.server.common.exception.GoSoParseException;
import com.gosociety.server.common.model.GoSoSceneScreenEvent;
import com.gosociety.server.common.model.GoSoSceneScreenRecommendation;
import com.gosociety.server.services.external.bing.BingReverseGeoCodeResolver;
import com.gosociety.server.services.json.BaseJsonResponse;
import com.gosociety.server.services.json.SocietySceneScreenJsonResponse;
import com.gosociety.server.services.model.GoSoBingReverseGeoCodeResponse;
import com.gosociety.server.services.model.BasicGeoLocationInputs;

@Controller
@RequestMapping("/sceneinfo")
public class GoSoSceneController {
	
	private static final Logger logger = Logger.getLogger(GoSoSceneController.class);
	
	private BingReverseGeoCodeResolver brgcr;
	private SceneScreenDao ssd;

	public BingReverseGeoCodeResolver getBrgcr() {
		return brgcr;
	}

	public void setBrgcr(BingReverseGeoCodeResolver brgcr) {
		this.brgcr = brgcr;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public SocietySceneScreenJsonResponse getSceneData(@RequestBody BasicGeoLocationInputs input) {
		
		SocietySceneScreenJsonResponse response = new SocietySceneScreenJsonResponse();
		
		try {
			GoSoBingReverseGeoCodeResponse bingResponse = brgcr.resolveReverseGeoCode(input.getLatlong());
			
			if (bingResponse!=null) {
			
				String usercity = bingResponse.getCity();
				String userstate = bingResponse.getState();
			
				List<GoSoSceneScreenRecommendation> recs = ssd.getRecommendations(input.getSocid(),usercity,userstate);
				response.setRecommendations(recs);
			
				List<GoSoSceneScreenEvent> events = ssd.getEvents(input.getSocid(),usercity,userstate);
				response.setEvents(events);

				return response;
			}
			
			else {
				response.setError(BaseJsonResponse.BING_SERVICES_UNREACHABLE);
				return response;
			}
			
		}catch (GoSoHttpClientException e) {
			response.setError(BaseJsonResponse.BING_SERVICES_UNREACHABLE);
			return response;
			
		} catch (GoSoParseException e) {
			response.setError(BaseJsonResponse.UNABLE_TO_PARSE_REVERSE_GEOCODE_BING);
			return response;
		}
	}

	public SceneScreenDao getSsd() {
		return ssd;
	}

	public void setSsd(SceneScreenDao ssd) {
		this.ssd = ssd;
	}
}