package com.mangokiwi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by tangmaolei on 5/9/17.
 */
public enum TeacherStatus {
	YES("yes"), NO("no"), PENDING("pending");

	private String text;

	TeacherStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@JsonCreator
	public static TeacherStatus create(String text){
		if(text == null)
			throw new IllegalArgumentException("input string is null");
		for(TeacherStatus status : TeacherStatus.values()){
			if(status.getText().equals(text)){
				return status;
			}
		}
		throw new IllegalArgumentException("no matching enum for input string ");
	}

	@Override
	@JsonValue
	public String toString(){return text;}
}
