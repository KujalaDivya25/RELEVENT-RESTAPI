package com.rest.dto;

import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class Blog {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int blogId;
	private String title;
	private String category;
	private Date blogDate = new Date();
	@Column(length=3000)
	private String content;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="professionalId")
	private Professional professional;
	
	private String blogImage;
	
	public Blog() {
		super();
	}


	public Blog(int blogId, String title, String category, Date blogDate, String content, User user,
			Professional professional, String blogImage) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.category = category;
		this.blogDate = blogDate;
		this.content = content;
		this.user = user;
		this.professional = professional;
		this.blogImage = blogImage;
	}


	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUserblog() {
		return user;
	}

	public void setUserblog(User user) {
		this.user = user;
	}

	public Professional getProfblog() {
		return professional;
	}

	public void setProfblog(Professional professional) {
		this.professional = professional;
	}

	public Date getBlogDate() {
		return blogDate;
	}


	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Professional getProfessional() {
		return professional;
	}


	public void setProfessional(Professional professional) {
		this.professional = professional;
	}


	public String getBlogImage() {
		return blogImage;
	}


	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}


}