package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Professional {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int professionalId;
	private String professionalName;
	private String serviceName;
	private String serviceType;
	private int experience;
	private String address;
	private String mobile;
	private String mailId;
	private String password;
	private String serviceImage;

	@JsonIgnore
	@JsonManagedReference
	@ManyToMany(mappedBy = "professionalList",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CreateEvent> EventList = new ArrayList<CreateEvent>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "professional", cascade = {CascadeType.ALL})
	private List<Blog> blogList = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "professional", cascade = {CascadeType.ALL})
	private List<Experience> expList = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "professional", cascade = {CascadeType.ALL})
	private List<Rating> ratingList = new ArrayList<>();

	
	public Professional() {
		super();
	}
	
	public Professional(int professionalId, String professionalName, String serviceName, String serviceType,
			int experience, String address, String mobile, String mailId, String password) {
		super();
		this.professionalId = professionalId;
		this.professionalName = professionalName;
		this.serviceName = serviceName;
		this.serviceType = serviceType;
		this.experience = experience;
		this.address = address;
		this.mobile = mobile;
		this.mailId = mailId;
		this.password = password;
	}
		
	public int getProfessionalId() {
		return professionalId;
	}
	public void setProfessionalId(int professionalId) {
		this.professionalId = professionalId;
	}
	public String getProfessionalName() {
		return professionalName;
	}
	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

	public List<Experience> getExpList() {
		return expList;
	}

	public void setExpList(List<Experience> expList) {
		this.expList = expList;
	}
	
	public List<CreateEvent> getEventList() {
		return EventList;
	}

	public void setEventList(List<CreateEvent> eventList) {
		EventList = eventList;
	}

	public List<Rating> getRatingList() {
		return ratingList;
	}

	public void setRatingList(List<Rating> ratingList) {
		this.ratingList = ratingList;
	}

}
