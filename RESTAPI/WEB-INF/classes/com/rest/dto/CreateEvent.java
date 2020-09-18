package com.rest.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "eventId")
public class CreateEvent {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int eventId;
	private String eventName;	
	private String category;
	private String eventType;
	private String eventStartDate;
	private String eventEndDate;
	private String organiserName;
	private String venue;
	
	@Column(length=3000)
	private String about;
	
	private String startTime;
	private String endTime;
	
	@Column(length=3000)
	private String startOverview;
	@Column(length=3000)
	private String endOverview;
	
	private String time1;
	private String time2;
	
	@Column(length=3000)
	private String overview1;
	@Column(length=3000)
	private String overview2;
	
	private String sponsor;
	private int ticketPrice;
	private int attendeesCount;
	private String poster;
	private String Guest1;
	private String Guest2;
	private String Guest3;
	


	@ManyToMany	
	@JoinTable(name="EventProfessional",joinColumns={@JoinColumn(name="eventId")}, 
	inverseJoinColumns = { @JoinColumn(name = "professionalId")})
	@JsonBackReference
	private List<Professional> professionalList = new ArrayList<Professional>();

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="organiserId")
	private User user;

	@OneToMany(mappedBy = "event", cascade = {CascadeType.ALL})
	private List<Payment> paymentList = new ArrayList<>();
	
	public CreateEvent() {
		super();
	}

	public CreateEvent(int eventId, String eventName, String category, String eventType, String eventStartDate,
			String eventEndDate, String organiserName, String venue, String about, String startTime, String endTime,
			String startOverview, String endOverview, String time1, String time2, String overview1, String overview2,
			String sponsor, int ticketPrice, int attendeesCount, String poster, String guest1, String guest2,
			String guest3, List<Professional> professionalList, User user, List<Payment> paymentList) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.category = category;
		this.eventType = eventType;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.organiserName = organiserName;
		this.venue = venue;
		this.about = about;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startOverview = startOverview;
		this.endOverview = endOverview;
		this.time1 = time1;
		this.time2 = time2;
		this.overview1 = overview1;
		this.overview2 = overview2;
		this.sponsor = sponsor;
		this.ticketPrice = ticketPrice;
		this.attendeesCount = attendeesCount;
		this.poster = poster;
		Guest1 = guest1;
		Guest2 = guest2;
		Guest3 = guest3;
		this.professionalList = professionalList;
		this.user = user;
		this.paymentList = paymentList;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public String getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public String getOrganiserName() {
		return organiserName;
	}

	public void setOrganiserName(String organiserName) {
		this.organiserName = organiserName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartOverview() {
		return startOverview;
	}

	public void setStartOverview(String startOverview) {
		this.startOverview = startOverview;
	}

	public String getEndOverview() {
		return endOverview;
	}

	public void setEndOverview(String endOverview) {
		this.endOverview = endOverview;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getOverview1() {
		return overview1;
	}

	public void setOverview1(String overview1) {
		this.overview1 = overview1;
	}

	public String getOverview2() {
		return overview2;
	}

	public void setOverview2(String overview2) {
		this.overview2 = overview2;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getAttendeesCount() {
		return attendeesCount;
	}

	public void setAttendeesCount(int attendeesCount) {
		this.attendeesCount = attendeesCount;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getGuest1() {
		return Guest1;
	}

	public void setGuest1(String guest1) {
		Guest1 = guest1;
	}

	public String getGuest2() {
		return Guest2;
	}

	public void setGuest2(String guest2) {
		Guest2 = guest2;
	}

	public String getGuest3() {
		return Guest3;
	}

	public void setGuest3(String guest3) {
		Guest3 = guest3;
	}

	public List<Professional> getProfessionalList() {
		return professionalList;
	}

	public void setProfessionalList(List<Professional> professionalList) {
		this.professionalList = professionalList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

}
