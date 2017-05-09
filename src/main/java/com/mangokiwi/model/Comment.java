package com.mangokiwi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tangmaolei on 5/8/17.
 */
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User student;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User teacher;

	private String comment;

	private Date date;

	public Comment(User student, User teacher, String comment, Date date) {
		this.student = student;
		this.teacher = teacher;
		this.comment = comment;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
