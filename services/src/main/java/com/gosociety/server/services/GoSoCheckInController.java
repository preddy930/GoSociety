package com.gosociety.server.services;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.CheckInDao;
import com.gosociety.server.common.dao.PlacesDao;
import com.gosociety.server.common.dao.UserDao;
import com.gosociety.server.common.exception.GoSoHttpClientException;
import com.gosociety.server.common.facebook.FacebookConstants;
import com.gosociety.server.common.model.CheckIn;
import com.gosociety.server.common.model.Place;
import com.gosociety.server.common.model.User;
import com.gosociety.server.common.util.GoSoHttpClient;
import com.gosociety.server.services.json.GoSoCheckInResponse;
import com.gosociety.server.services.model.GoSoCheckInControllerInputs;

//TODO:We are getting latitude and longitude in the inputs, we'll have to verify that the user is still
//near that location.

@Controller
@RequestMapping("/checkin")
public class GoSoCheckInController {
	
	private CheckInDao checkInDao;
	private UserDao userDao;
	private PlacesDao placesDao;
	private GoSoHttpClient client;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public GoSoCheckInResponse checkIn(@RequestBody GoSoCheckInControllerInputs inputs) {
		
		User user = userDao.getUser(inputs.getUid());
		Place p = placesDao.findPlaceById(inputs.getPid());
		
		long checkintime = Calendar.getInstance().getTimeInMillis();
		
		if (validateCheckinTimeConstraints(user, inputs.getPid(), checkintime)) {
			checkInDao.recordCheckIn(inputs.getPid(), inputs.getUid(), inputs.getNote(), checkintime, 1);
		}
		
		else {
			checkInDao.recordCheckIn(inputs.getPid(), inputs.getUid(), inputs.getNote(), checkintime, 0);
		}
		
		if (inputs.isFacebookShare()) {
			
			String path = FacebookConstants.FB_GRAPH_WALL_PATH;
			String [] values = {user.getFacebookId()};
			
			MessageFormat form = new MessageFormat(path);
			path = form.format(values);
			
			Map<String, String> checkinparams = new HashMap<String,String>();
			checkinparams.put("message",user.getFname() + " is currently at " + p.getName() + " in " + p.getAddress().getCity()+", "+p.getAddress().getState() + 
					"and he shouts:"+inputs.getNote());
			checkinparams.put("access_token",user.getFacebookAccessToken());
			
			try {
				client.postRequest(FacebookConstants.FB_GRAPH_DOMAIN, path, checkinparams, true);
			} catch (GoSoHttpClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/*If the user has checked into this location in the past 2 hours, it will not be sent to the game mechanic and
	 * it will not be used in any aggregate counts of checkins
	 */
	private boolean validateCheckinTimeConstraints(User user, String pid, long checkintime) {
		
		List <ObjectId> checkins = user.getCheckins();
		long largestcheckintime = 0;
		
		if (checkins != null) {
			for (ObjectId usercheckin : checkins) {
			
				CheckIn checkin = checkInDao.getCheckInDetails(usercheckin);
			
				if(checkin.getPid().toString().equals(pid)) {
				
					Date previouscheckintime = checkin.getCheckintime();
					long time = previouscheckintime.getTime();
				
					//find the largest checkin time for this venue
					if (time>largestcheckintime) {
						largestcheckintime = time;
					}
				}
			}
		}
		
		if (largestcheckintime==0) {
			return true;
		}
		
		//4 hour time difference before the last checkin
		else if ( (checkintime-largestcheckintime)>=7200000) {
			return true;
		}
		
		else {
			return false;
		}
	}

	public CheckInDao getCheckInDao() {
		return checkInDao;
	}

	public void setCheckInDao(CheckInDao checkInDao) {
		this.checkInDao = checkInDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public GoSoHttpClient getClient() {
		return client;
	}

	public void setClient(GoSoHttpClient client) {
		this.client = client;
	}

	public PlacesDao getPlacesDao() {
		return placesDao;
	}

	public void setPlacesDao(PlacesDao placesDao) {
		this.placesDao = placesDao;
	}
}
