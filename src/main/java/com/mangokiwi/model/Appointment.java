package com.mangokiwi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhenfeng on 5/8/17.
 */


@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    private Double rating;

    @Enumerated(EnumType.ORDINAL)
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
}
