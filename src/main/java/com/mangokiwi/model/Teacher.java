package com.mangokiwi.model;

import javax.persistence.*;

/**
 * Created by tangmaolei on 5/8/17.
 */
@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Double rating;

	private Integer count;

	private String diploma;

	private String resume;

//	TODO: available time


	public Teacher(){};

	public Teacher(User user, Double rating, Integer count, String diploma, String resume) {
		this.user = user;
		this.rating = rating;
		this.count = count;
		this.diploma = diploma;
		this.resume = resume;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}
