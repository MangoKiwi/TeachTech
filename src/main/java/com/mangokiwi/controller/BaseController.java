package com.mangokiwi.controller;

/**
 * Created by tangmaolei on 5/8/17.
 */

//REST API STRUCTURE
public abstract class BaseController {
	private static final String BASE_PATH = "/TeachTech/v1";
	public static final String HEALTH_URL = BASE_PATH + "/health";

	/*============================================================================
		Appointment
	 ============================================================================*/
	public static final String APPOINTMENT_PATH = BASE_PATH + "/appointments";
		/*
		    example unit response format:

		    {
		    	"id":1,
		    	"student":
		    	{
                    "id": 103981930177025,
                    "username": "TML",
                    "firstName": "maolei",
                    "lastName": "tang",
                    "gender": "male",
                    "birthDate": "1992-12-31 12:00:00",
                    "province": "beijing",
                    "city": "beijing",
                    "district": "xicheng",
                    "degree": "bachelor"
                    "isTeacher" : "yes"
		    	},
		    	"teacher":
		    	{
                    "id": 103981930177025,
                    "username": "TML",
                    "firstName": "maolei",
                    "lastName": "tang",
                    "gender": "male",
                    "birthDate": "1992-12-31 12:00:00",
                    "province": "beijing",
                    "city": "beijing",
                    "district": "xicheng",
                    "degree": "bachelor"
                    "isTeacher" : "yes"
		    	},
		    	"rating":5,
		    	"status":"finished",
		    	"date":"2017-05-11 11:05:00"
		    }

			-----------------------------------------------------------------
			** Get all appointments **
			url: /TeachTech/v1/appointments
			method:get
			response format:
				list of unit example response
			-----------------------------------------------------------------
			** Get a appointment with id **
			url: /TeachTech/v1/user/appointments/{id}
			method:get
			response format:
				unit example response
			-----------------------------------------------------------------
			** Get all appointments for a student **
			url: /TeachTech/v1/appointments
			method:get
			request body format :
			{
				"student_id":103981930177025
			}
			response format:
				list of unit example response
			-----------------------------------------------------------------
			** Get all appointments for a teacher **
			url: /TeachTech/v1/appointments
			method:get
			request body format :
			{
				"teacher_id":123456789123456
			}
			response format:
				list of unit example response
			-----------------------------------------------------------------
			** Get all appointment for a student and a teacher **
			url: /TeachTech/v1/appointments
			method:get
			request body format :
			{
				"student_id":103981930177025,
				"teacher_id":123456789123456
			}
			response format:
				list of unit example response
			-----------------------------------------------------------------
			** Create a new appointment **
			upon creating default status will be SCHEDULED
			url: /TeachTech/v1/appointments
			method:post
			request body format :
			{
				"student_id":103981930177025,
				"teacher_id":123456789123456,
				"date":"2017-05-11 11:05:00"
			}
			response format:
				unit example response
			-----------------------------------------------------------------
			** Update a new appointment**
			url: /TeachTech/v1/appointments
			method:put
			request body format :
			{
				"student_id":103981930177025,
				"teacher_id":123456789123456,
				"status" : "finished",
				"rating" : 5, (can be NULL)
				"date":"2017-05-11 11:05:00",
			}
			response format:
				unit example response
			example usage:
				status change update is encapsulated in the request body to
				avoid include un-http verb in the uri to achieve REST standard
				for example
				if request body look like this
				{
					"student_id":103981930177025,
					"teacher_id":123456789123456,
					"status" : "finished",
					"rating" : 5
					"date":"2017-05-11 11:05:00",
				}
				then we know user finished the appointment and leave a rate
				if request body look like this
				{
					"student_id":103981930177025,
					"teacher_id":123456789123456,
					"status" : "expect_rating",
					"rating" : NULL
					"date":"2017-05-11 11:05:00",
				}
				then we know user finished the appointment and didn't leave rate
				if request body look like this
				{
					"student_id":103981930177025,
					"teacher_id":123456789123456,
					"status" : "scheduled",
					"rating" : NULL
					"date":"2017-05-12 11:05:00",
				}
				then we know user want to change the appointment time
				use status code to achieve rest design

			-----------------------------------------------------------------
			** Cancel a new appointment **
			url: /TeachTech/v1/appointments/{id}
			method:delete
			response format:
				unit example response

		 */


	/*============================================================================
		User
	 ============================================================================*/
	public static final String USER_PROFILE_PATH = BASE_PATH + "/users";
		/*
			example user unit response
		    {
                "id": 103981930177025,
                "username": "ZF",
                "firstName": "Zhen",
                "lastName": "Feng",
                "gender": "male",
                "birthDate": "1993-4-21 12:00:00",
                "province": "beijing",
                "city": "beijing",
                "district": "xicheng",
                "degree": "bachelor"
                "isTeacher" : "yes"
		    }

		    example tag unit response
		    {
                "id": 1
                "user_id": 103981930177025
                "tag" : "computer science"
		    }
		    -----------------------------------------------------------------
			** Get all users **
			url: /TeachTech/v1/users
			method:get
			response format:
				list of user unit example response
			-----------------------------------------------------------------
			** Get a user with id **
			url: /TeachTech/v1/users/{id}
			method:get
			response format:
				user unit example response
			-----------------------------------------------------------------
			** Get a user's all tag **
			url: /TeachTech/v1/users/{id}/tags
			method:get
			response format:
				list of tag unit example response

			-----------------------------------------------------------------
			** Create a new user **
			url: /TeachTech/v1/users
			method:post
			request body format
			{
				"username": "ZF",
				"firstName": "Zhen",
				"lastName": "Feng",
				"gender": "male",
				"birthDate": "1993-4-21 12:00:00",
				"province": "beijing",
				"city": "beijing",
				"district": "xicheng",
				"degree": "bachelor"
				"isTeacher" : "no"
		    }
			response format:
				 user unit example response
			-----------------------------------------------------------------
			** Update a user **
			url: /TeachTech/v1/users/{id}
			method:put
			response format:
				 user unit example response
			example:
				depends on the request body's parameter, we can
				decide which user part to update . By saying that,
				previous  FILL_UP_INFO_UR and  UPDATE_INFO_URL
				can all be combined into this one uri mapping
				for example
				{
					"birthDate": "1993-4-21 12:00:00",
					"province": "beijing",
					"isTeacher" : "no"
		    	}
		    	means user only want to update this three info
			-----------------------------------------------------------------
			** Delete a user **
			url: /TeachTech/v1/users/{id}
			method:delete
			response format:
				 user unit example response

		 */
	/*============================================================================
		Teacher
	 ============================================================================*/
	public static final String TEACHER_PROFILE_PATH = BASE_PATH + "/teachers";
	/*============================================================================
		Comments
	 ============================================================================*/
	public static final String COMMENT_PATH = BASE_PATH + "/comments";


}


//
//	public static final String INFO_PATH = USER_PROFILE_PATH + "/info";
//	public static final String TEACHER_PATH = USER_PROFILE_PATH + "/teacher";
//	public static final String TAG_PATH = USER_PROFILE_PATH + "/tag";
//
//	public static final String FILL_UP_INFO_URL = INFO_PATH + "/fill";
//	public static final String UPDATE_INFO_URL = INFO_PATH + "/update";
//	public static final String FIND_TEACHER_URL = TEACHER_PATH + "/retrieve";
//	/*==============================================================================
//	get teacher info:
//	url: /TeachTech/v1/user/teacher/retrieve
//	method: GET
//	response format:
//		content:
//		{
//			"id": 1,
//			"user":
//			{
//				"id": 103981930177025,
//				"username": "TML",
//				"firstName": "maolei",
//				"lastName": "tang",
//				"gender": "male",
//				"birthDate": "1992-12-31 12:00:00",
//				"province": "beijing",
//				"city": "beijing",
//				"district": "xicheng",
//				"degree": "bachelor"
//		  	},
//			"rating": 4.2,
//			"count": 0,
//			"diploma": "/Document/diploma",
//			"resume": "/Document/resume"
//		}
//	==============================================================================*/
////	public static final String UPDATE_TEACHER_URL = TEACHER_PATH + "/update";
//	/*==============================================================================
//	update teacher info:
//	url: /TeachTech/v1/user/teacher/update
//	method: PUT
//	request format:
//		content:
//		{
//			"district": "xicheng",
//			"degree": "bachelor"
//		}
//	==============================================================================*/
//	public static final String UPLOAD_TEACHER_DIPLOMA_FILE = TEACHER_PATH + "/upload_diploma_file";
//	/*==============================================================================
//	upload teacher diploma file:
//	url: /TeachTech/v1/user/teacher/upload_diploma_file
//	method: PUT
//	request format:
//		content:
//		{
//			"diploma": diploma.pdf
//		}
//	response format:
//		String (file name)
//	==============================================================================*/
//	public static final String UPLOAD_TEACHER_RESUME_FILE = TEACHER_PATH + "/upload_resume_file";
//	/*==============================================================================
//	upload teacher resume file:
//	url: /TeachTech/v1/user/teacher/upload_resume_file
//	method: PUT
//	request format:
//		content:
//		{
//			"resume": resume.pdf
//		}
//	response format:
//		String (file name)
//	==============================================================================*/
//
//	/*============================================================================
//		Tag
//	 ============================================================================*/
//	public static final String UPDATE_TAG_URL = TAG_PATH + "/update";
