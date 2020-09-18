package com.ts.dao;

import java.util.List;

import com.rest.dto.Rating;
import com.ts.db.HibernateTemplate;

public class RatingDAO {

	public int addRating(Rating rate) {

		return HibernateTemplate.addObject(rate);
	}

	public static List<Object> getallRatings(int professionalId) {
		return HibernateTemplate.getObjectListByQuery("from Rating where professionalId=" + professionalId);
	}

}