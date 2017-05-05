package com.mangokiwi.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by zhenfeng on 5/3/17.
 */
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date issueAt;

    private Date expireAt;


    public Token(String accessToken, User user, Date issueAt, Date expireAt) {
        this.accessToken = accessToken;
        this.user = user;
        this.issueAt = issueAt;
        this.expireAt = expireAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getIssueAt() {
        return issueAt;
    }

    public void setIssueAt(Date issueAt) {
        this.issueAt = issueAt;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
