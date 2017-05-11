package com.mangokiwi.controller;

import com.mangokiwi.model.Teacher;
import com.mangokiwi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * Created by tangmaolei on 5/9/17.
 */
@RestController
public class TeacherController extends BaseController {
//	@Autowired
//	private TeacherService teacherService;
//
//	@RequestMapping(value = FIND_TEACHER_URL, method = RequestMethod.GET)
//	public Teacher getTeacher(@RequestHeader("Auth-Token") String accessToken,
//							  @RequestHeader("Auth-Vendor") String vendor) {
//		return teacherService.getTeacherByAccessTokenAndVendor(accessToken, vendor);
//	}
//
//	@RequestMapping(value = UPLOAD_TEACHER_DIPLOMA_FILE, method = RequestMethod.PUT)
//	public String uploadDiploma(@RequestHeader("Auth-Token") String accessToken,
//								 @RequestHeader("Auth-Vendor") String vendor) {
//		return "fake diploma name";
//	}
//
//	@RequestMapping(value = UPLOAD_TEACHER_RESUME_FILE, method = RequestMethod.PUT)
//	public String updateResume(@RequestHeader("Auth-Token") String accessToken,
//								 @RequestHeader("Auth-Vendor") String vendor) {
//		return "fake resume name";
//	}
}
