package com.rest.dto;

import java.util.ArrayList;

import java.util.List;
import com.rest.dto.Payment;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String contact;
	private String emailId;
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
	private List<CreateEvent> eventList = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
	private List<Payment> paymentList = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
	private List<Blog> blogList = new ArrayList<>();
	public User() {
		super();
	}

	public User(int userId, String userName, String contact, String emailId, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.contact = contact;
		this.emailId = emailId;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CreateEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<CreateEvent> eventList) {
		this.eventList = eventList;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}
	
}
