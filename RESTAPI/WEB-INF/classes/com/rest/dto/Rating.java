package com.rest.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Rating {

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ratingId;
	private int rating;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="professionalId")
	private Professional professional ;

	public Rating() {
		super();
		
	}

	public Rating(int ratingId, int rating, Professional professional) {
		super();
		this.ratingId = ratingId;
		this.rating = rating;
		this.professional = professional;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
	
	

	
}
