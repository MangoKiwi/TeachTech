package com.mangokiwi.service;

import com.mangokiwi.core.annotation.HandleEntityNotFound;
import com.mangokiwi.model.Teacher;
import com.mangokiwi.model.User;
import com.mangokiwi.repository.TeacherRepository;
import com.mangokiwi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tangmaolei on 5/9/17.
 */
@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@HandleEntityNotFound
	public Teacher getTeacherByUserId(Long id){
		Teacher teacher = teacherRepository.findById(id);
		return teacher;
	}

	@HandleEntityNotFound
	public Teacher getTeacherByUser(User user){
		Long userId = user.getId();
		return getTeacherByUserId(userId);
	}

	@HandleEntityNotFound
	public Teacher getTeacherByAccessTokenAndVendor(String accessToken, String vendor){
		User user = userService.getUserByAccessTokenAndVendor(accessToken, vendor);
		return getTeacherByUser(user);
	}
}
