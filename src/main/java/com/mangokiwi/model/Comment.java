package com.mangokiwi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;

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

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private User student;

	@Column(name = "student_id", insertable = false, updatable = false)
	private Long studentId;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private User teacher;

	@Column(name = "teacher_id", insertable = false, updatable = false)
	private Long teacherId;

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

	public Long getStudentId() {
		return studentId;
	}

	public Long getTeacherId() {
		return teacherId;
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
