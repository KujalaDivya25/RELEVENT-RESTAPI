package com.ts.db;

import java.io.Serializable;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.rest.dto.CreateEvent;
import com.rest.dto.Professional;
import com.rest.dto.Rating;
import com.rest.dto.User;


public class HibernateTemplate {

	private static SessionFactory sessionFactory;

	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public static int addObject(Object obj){
		System.out.println("Inside Template...");
		int result=0;

		Transaction tx=null;

		try {

			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();

			session.merge(obj);

			tx.commit();

			result=1;

		} catch (Exception e) {

			tx.rollback();

			e.printStackTrace();
		}

		return result;
	}

	public static Object getObject(Class c,Serializable serializable)
	{
		Object obj=null;

		try {			
			return sessionFactory.openSession().get(c,serializable);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return obj;
	}

	public static List<Object> getObjectListByQuery(String query)
	{
		return sessionFactory.openSession().createQuery(query).list();
	}

	public static int updateObject(Object obj)
	{
		int result=0;

		Transaction tx=null;

		try {

			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();

			session.saveOrUpdate(obj);

			tx.commit();

			result=1;

		} catch (Exception e) {

			tx.rollback();

			e.printStackTrace();
		}

		return result;
	}

	public static int deleteObject(Class c,Serializable serializable)
	{
		int result=0;

		Session session=sessionFactory.openSession();

		Transaction tx=session.beginTransaction();

		try {

			Object obj=session.get(c,serializable);

			session.delete(obj);

			tx.commit();

			result=1;

		} catch (Exception e) {

			e.printStackTrace();

			tx.rollback();
		}

		return result;
	}

	public static List<Object> getObjectListByName(Class c, String columName, String value) {
		Session session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(c);
		Criterion nameCriterion = Restrictions.eq(columName, value);
		criteria.add(nameCriterion);
		return criteria.list();
	}

	public static Object getObjectByUserPass(String userName,String password) {

		String queryString = "from User where userName =:userName and password =:password";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("userName", userName);
		query.setString("password", password);
		Object queryResult = query.uniqueResult();
		User user = (User)queryResult;
		System.out.println(user);
		return user; 
	}

	public static List<Object> getEventList(String category,String eventType) {

		String queryString = "from CreateEvent where category =:category and eventType =:eventType";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("category", category);
		query.setString("eventType", eventType);
		List<Object> queryResult = (List<Object>) query.list();
		List<Object> event = queryResult;
		System.out.println(event);
		return event; 
	}

	public static Object getUserByUserId(int userId) {

		String queryString = "from User where userId=:userId";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setInteger("userId", userId);

		Object queryResult = query.uniqueResult();
		User user = (User)queryResult;
		System.out.println(user);
		return user; 
	}
	public static Object getProfByProfId(int professionalId) {

		String queryString = "from Professional where professionalId=:professionalId";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setInteger("professionalId", professionalId);

		Object queryResult = query.uniqueResult();
		Professional prof = (Professional)queryResult;
		System.out.println(prof);
		return prof; 
	}


	public static Object getObjectByProfessionalPass(String professionalName, String password) {
		String queryString = "from Professional where professionalName =:professionalName and password =:password";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("professionalName", professionalName);
		query.setString("password", password);
		Object queryResult = query.uniqueResult();
		Professional prof = (Professional)queryResult;
		System.out.println(prof);
		return prof; 
	}
	public static Object getObjectByuserId(String professionalName, String password) {
		String queryString = "from Professional where professionalName =:professionalName and password =:password";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("professionalName", professionalName);
		query.setString("password", password);
		Object queryResult = query.uniqueResult();
		Professional prof = (Professional)queryResult;
		System.out.println(prof);
		return prof; 
	}
	public static Object getLoggedUserByUserName(String userName) {

		String queryString = "from User where userName=:userName";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("userName", userName);

		Object queryResult = query.uniqueResult();
		User user = (User)queryResult;
		System.out.println(user);
		return user;
	}
	public static Object getLoggedProf(String professionalName) {

		String queryString = "from Professional where professionalName=:professionalName";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("professionalName", professionalName);

		Object queryResult = query.uniqueResult();
		Professional professional = (Professional)queryResult;
		System.out.println(professional);
		return professional;
	}

}
