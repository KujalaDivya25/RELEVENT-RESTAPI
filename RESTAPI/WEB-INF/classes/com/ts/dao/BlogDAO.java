package com.ts.dao;

import java.util.List;

import com.rest.dto.Blog;
import com.ts.db.HibernateTemplate;

public class BlogDAO {

	public int addBlog(Blog blog) {
		return HibernateTemplate.addObject(blog);
	}

	public List<Object> getListOfBlogs() {
		return HibernateTemplate.getObjectListByQuery("from Blog");
	}

	public static List<Object> UserBlogs(int userId) {
		return HibernateTemplate.getObjectListByQuery("from Blog where userId=" + userId);
	}

	public Object ProfBlogs(int professionalId) {
		return HibernateTemplate.getObjectListByQuery("from Blog where professionalId=" + professionalId);
	}
}