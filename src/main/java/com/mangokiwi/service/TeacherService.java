package com.mangokiwi.service;

import com.mangokiwi.core.annotation.HandleEntityNotFound;
import com.mangokiwi.core.annotation.HandleTeacherAlreadyExist;
import com.mangokiwi.core.exception.EntityNotFoundException;
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
	private UserService userService;

	public Teacher validateTeacherByUserId(Long userId){
		Teacher teacher = teacherRepository.findById(userId);
		return teacher;
	}

	@HandleEntityNotFound
	public Teacher getTeacherByUserId(Long userId){
		Teacher teacher = teacherRepository.findById(userId);
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

//	TODO: check if already be a teacher
//	@HandleTeacherAlreadyExist
	public Teacher addTeacherByUserId(Long userId){
		User user = userService.getUserById(userId);
		Teacher teacher = new Teacher(user);
		return teacherRepository.save(teacher);
	}

	public Teacher update(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

//	dont need to deal with wrong file type
	public Teacher update(Teacher teacher, String fileType, String filename) {
		if (fileType.equals("resume"))
			teacher.setResume(filename);
		else
			teacher.setDiploma(filename);
		return teacherRepository.save(teacher);
	}

	public Teacher incrCount(Teacher teacher) {
		int count = teacher.getCount();
		count++;
		teacher.setCount(count);
		return teacherRepository.save(teacher);
	}
}
