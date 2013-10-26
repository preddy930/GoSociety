package com.gosociety.server.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gosociety.server.common.dao.EventDetailsDao;
import com.gosociety.server.common.model.EventDetail;
import com.gosociety.server.services.json.GoSoEventDetailResponse;
import com.gosociety.server.services.model.GoSoEventDetailsInputs;

@Controller
@RequestMapping("/eventdetail")
public class GoSoEventDetailsController {
	
	EventDetailsDao eventDetailsDao;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public GoSoEventDetailResponse getEventDetail(@RequestBody GoSoEventDetailsInputs event) {
		EventDetail ed = eventDetailsDao.getEventDetails(event.getEid());
		GoSoEventDetailResponse response = new GoSoEventDetailResponse(ed);
		return response;
	}

	public EventDetailsDao getEventDetailsDao() {
		return eventDetailsDao;
	}

	public void setEventDetailsDao(EventDetailsDao eventDetailsDao) {
		this.eventDetailsDao = eventDetailsDao;
	}
}