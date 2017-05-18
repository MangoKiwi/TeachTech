package com.mangokiwi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mangokiwi.controller.BaseController;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhenfeng on 5/8/17.
 */


@Entity
public class Appointment extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Long studentId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Column(name = "teacher_id", insertable = false, updatable = false)
    private Long teacherId;

    private Double rating;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private Date date;

    public Appointment(){}

    public Appointment(User student, User teacher, Double rating, AppointmentStatus status, Date date) {
        this.student = student;
        this.teacher = teacher;
        this.rating = rating;
        this.status = status;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
