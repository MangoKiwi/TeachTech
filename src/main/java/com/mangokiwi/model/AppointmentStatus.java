package com.mangokiwi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.stereotype.Component;

/**
 * Created by zhenfeng on 5/8/17.
 */


public enum AppointmentStatus {
    FINISHED("finished"),SCHEDULED("scheduled"),EXPECT_RATING("expect_rating");

    private String text;

    AppointmentStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static AppointmentStatus create(String text){
        if(text == null)
            throw new IllegalArgumentException("input string is null");
        for(AppointmentStatus status : AppointmentStatus.values()){
            if(status.getText().equals(text))
                return status;
        }
        throw new IllegalArgumentException("no matching enum for input string");
    }

    @Override
    @JsonValue
    public String toString(){return text;}
}
