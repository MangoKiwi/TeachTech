package com.mangokiwi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.stereotype.Component;

/**
 * Created by zhenfeng on 5/8/17.
 */

public enum Gender{
    MALE("male"), FEMALE("female");

    private String text;

    Gender(String text){
        this.text = text;
    }
    public String getText(){return this.text;}

    @JsonCreator
    public static Gender create(String text){
        if(text == null)
            throw new IllegalArgumentException("input string is null");
        for(Gender gender : Gender.values()){
            if(gender.getText().equals(text)){
                return gender;
            }
        }
        throw new IllegalArgumentException("no matching enum for input string");
    }

    @Override
    @JsonValue
    public String toString(){return text;}
}