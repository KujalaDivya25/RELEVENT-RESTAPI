package com.ts.dao;

import java.util.List;

import com.rest.dto.Blog;
import com.rest.dto.Experience;
import com.ts.db.HibernateTemplate;

public class ExperienceDAO {
	
	public int addExp(Experience exp) {
		return HibernateTemplate.addObject(exp);
	}
	
	public List<Object> getListOfExps() {
		return HibernateTemplate.getObjectListByQuery("from Experience");
	}

	public Object profExps(int professionalId) {
		return HibernateTemplate.getObjectListByQuery("from Experience where professionalId=" + professionalId);
	}
}