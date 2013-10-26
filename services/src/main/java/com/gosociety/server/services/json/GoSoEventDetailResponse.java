package com.gosociety.server.services.json;

import com.gosociety.server.common.model.EventDetail;

public class GoSoEventDetailResponse extends BaseJsonResponse {
	
	EventDetail eventDetails;
	
	public GoSoEventDetailResponse(EventDetail ed) {
		this.eventDetails=ed;
	}

	public EventDetail getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(EventDetail eventDetails) {
		this.eventDetails = eventDetails;
	}
}
