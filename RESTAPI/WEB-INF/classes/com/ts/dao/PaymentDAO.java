package com.ts.dao;

import java.util.List;

import com.rest.dto.Payment;
import com.ts.db.HibernateTemplate;

public class PaymentDAO {

	public int addPayment(Payment payment) {
		java.util.Date utilDate = new java.sql.Date(payment.getExpiryDate().getTime()); 
		return HibernateTemplate.addObject(payment);
	}


	public static List<Object> BookedUser(int regUserId) {
		return HibernateTemplate.getObjectListByQuery("from Payment where regUserId=" + regUserId);
	}

}