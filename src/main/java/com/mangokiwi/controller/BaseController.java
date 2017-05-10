package com.mangokiwi.controller;

/**
 * Created by tangmaolei on 5/8/17.
 */
public abstract class BaseController {
	private static final String BASE_PATH = "/TeachTech/v1";
	public static final String HEALTH_URL = BASE_PATH + "/health";

	/*============================================================================
		Appointment
	 ============================================================================*/
	public static final String APPOINTMENT_PATH = BASE_PATH + "/appointment";

	public static final String MAKE_APPOINTMENT_URL_ = APPOINTMENT_PATH + "/make";
	public static final String CANCEL_APPOINTMENT_URL = BASE_PATH + "/cancel";
	public static final String COMPLETE_APPOINTMENT_URL = BASE_PATH + "/complete";

	/*============================================================================
		User
	 ============================================================================*/
	public static final String USER_PROFILE_PATH = BASE_PATH + "/user";

	public static final String INFO_PATH = USER_PROFILE_PATH + "/info";
	public static final String TEACHER_PATH = USER_PROFILE_PATH + "/teacher";
	public static final String TAG_PATH = USER_PROFILE_PATH + "/tag";

	public static final String FILL_UP_INFO_URL = INFO_PATH + "/fill";
	public static final String UPDATE_INFO_URL = INFO_PATH + "/update";
	public static final String FIND_TEACHER_URL = TEACHER_PATH + "/retrieve";
	/*==============================================================================
	get teacher info:
	url: /TeachTech/v1/user/teacher/retrieve
	method: GET
	response format:
		content:
		{
			"id": 1,
			"user":
			{
				"id": 103981930177025,
				"username": "TML",
				"firstName": maolei,
				"lastName": tang,
				"gender": male,
				"birthDate": "1992-12-31 12:00:00",
				"province": beijing,
				"city": beijing,
				"district": xicheng,
				"degree": bachelor
		  	},
			"rating": 4.2,
			"count": 0,
			"diploma": "/Document/diploma",
			"resume": "/Document/resume"
		}
	==============================================================================*/
//	public static final String UPDATE_TEACHER_URL = TEACHER_PATH + "/update";
	/*==============================================================================
	update teacher info:
	url: /TeachTech/v1/user/teacher/update
	method: PUT
	request format:
		content:
		{
			"district": "xicheng",
			"degree": "bachelor"
		}
	==============================================================================*/
	public static final String UPLOAD_TEACHER_DIPLOMA_FILE = TEACHER_PATH + "/upload_diploma_file";
	/*==============================================================================
	upload teacher diploma file:
	url: /TeachTech/v1/user/teacher/upload_diploma_file
	method: PUT
	request format:
		content:
		{
			"diploma": diploma.pdf
		}
	response format:
		String (file name)
	==============================================================================*/
	public static final String UPLOAD_TEACHER_RESUME_FILE = TEACHER_PATH + "/upload_resume_file";
	/*==============================================================================
	upload teacher resume file:
	url: /TeachTech/v1/user/teacher/upload_resume_file
	method: PUT
	request format:
		content:
		{
			"resume": resume.pdf
		}
	response format:
		String (file name)
	==============================================================================*/
	public static final String UPDATE_TAG_URL = TAG_PATH + "/update";
}
