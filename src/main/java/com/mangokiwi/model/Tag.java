package com.mangokiwi.model;

import javax.persistence.*;

/**
 * Created by zhenfeng on 5/8/17.
 */

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String tag;

    public Tag(){}

    public Tag(User user, String tag) {
        this.user = user;
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
