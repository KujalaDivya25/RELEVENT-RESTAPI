package com.ts.dao;

import com.rest.dto.CreateEvent;
import com.rest.dto.User;
import com.ts.db.HibernateTemplate;

public class UserDAO {
	
	public int registerUser(User user) {
		return HibernateTemplate.addObject(user);
	}
	
	public Object getUser(String userName, String password) {
		return HibernateTemplate.getObjectByUserPass(userName, password);
	}
	public Object updateUser(User user) {
		return HibernateTemplate.updateObject(user);
	} 
	public static User getUserById(int userId)
	{
		return (User) HibernateTemplate.getUserByUserId(userId);
	}
	public static User getLoggedUser(String userName)
	{
		return (User) HibernateTemplate.getLoggedUserByUserName(userName);

	}
}