package com.ts.dao;


import java.util.List;

import com.rest.dto.CreateEvent;
import com.ts.db.HibernateTemplate;

public class CreateEventDAO {

	public int registerEvent(CreateEvent event) {
		return HibernateTemplate.addObject(event);
	}
	public static CreateEvent getEventById(int eventId)
	{
		return (CreateEvent) HibernateTemplate.getObject(CreateEvent.class, eventId);
	}
	public List<Object> getListOfEvents(String category, String eventType) {
		return HibernateTemplate.getEventList(category,eventType);
	}
	public static List<Object> EventonUserId(int organiserId) {
		return HibernateTemplate.getObjectListByQuery("from CreateEvent where organiserId=" + organiserId);
	}

}
