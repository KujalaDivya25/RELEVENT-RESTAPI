package com.ts.dao;

import java.util.List;

import com.rest.dto.Professional;
import com.rest.dto.Rating;
import com.rest.dto.User;
import com.ts.db.HibernateTemplate;

public class ProfessionalDAO {

	public int registerProfessional(Professional professional) {
		return HibernateTemplate.addObject(professional);
	}
	
	public Object getProfessional(String professionalName, String password) {
		return HibernateTemplate.getObjectByProfessionalPass(professionalName, password);
	}
	public static Professional getProfById(int professionalId)
	{
		return (Professional) HibernateTemplate.getProfByProfId(professionalId);
	}
	public List<Object> getListOfProfessional() {
		return HibernateTemplate.getObjectListByQuery("from Professional");
	}
	
	public static boolean getLoggedProf(String professionalName)
	{
		if(HibernateTemplate.getLoggedProf(professionalName) == null) {
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		return false;

	}

}
