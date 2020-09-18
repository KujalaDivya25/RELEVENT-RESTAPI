package com.rest.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity

public class Payment {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int paymentId;
	private String cardNum;
	private String nameOnCard;
	private Date expiryDate;
	private int amount;
	
	@ManyToOne
	@JoinColumn(name="eventId")
	private CreateEvent event;
	
	@ManyToOne
	@JoinColumn(name="regUserId")
	private User user;
	
	public Payment() {
		super();
	}

	public Payment(int paymentId, String cardNum, String nameOnCard, Date expiryDate, int amount) {
		super();
		this.paymentId = paymentId;
		this.cardNum = cardNum;
		this.nameOnCard = nameOnCard;
		this.expiryDate = expiryDate;
		this.amount = amount;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public CreateEvent getEvent() {
		return event;
	}

	public void setEvent(CreateEvent event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
