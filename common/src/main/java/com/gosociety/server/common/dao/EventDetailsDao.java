package com.gosociety.server.common.dao;

import com.gosociety.server.common.model.EventDetail;

public interface EventDetailsDao {
	
	public EventDetail getEventDetails (String eventId);

}
