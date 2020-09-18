package com.rest.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Experience {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int expId;
	
	@Column(length=3000)
	private String experience;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="professionalId")
	private Professional professional;
	
	public Experience() {
		super();
	}

	public Experience(String experience, Professional professional) {
		super();
		this.experience = experience;
		this.professional = professional;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

}