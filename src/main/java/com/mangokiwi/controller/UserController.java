package com.mangokiwi.controller;

import com.mangokiwi.core.annotation.HandleEmptyRequestBody;
import com.mangokiwi.model.Appointment;
import com.mangokiwi.model.Tag;
import com.mangokiwi.model.User;
import com.mangokiwi.service.AppointmentService;
import com.mangokiwi.service.TagService;
import com.mangokiwi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhenfeng on 5/11/17.
 */

@RestController
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private TagService tagService;


	@RequestMapping(value = USER_PROFILE_PATH + "/{id}", method = RequestMethod.GET)
	public User getUserWithId(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@RequestMapping(value = "/TeachTech/v1/tags/{id}", method = RequestMethod.GET)
	public Tag getTagWithId(@PathVariable Long id) {
		return tagService.getTagById(id);
	}

	@RequestMapping(value = USER_PROFILE_PATH + "/{id}/tags", method = RequestMethod.GET)
	public List<Tag> getAllTagsForUser(@PathVariable Long id) {
		return tagService.getTagByUserId(id);
	}

	@RequestMapping(value = USER_PROFILE_PATH + "/{id}", method = RequestMethod.PUT)
	@HandleEmptyRequestBody(includingFields = {"username", "firstName", "lastName", "gender","birthDate"})
	public User updateUserWithId(@PathVariable Long id, @RequestBody User user){
		user.setId(id);
		user = userService.update(user);
		return user;
	}
	@RequestMapping(value = USER_PROFILE_PATH + "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id){
		userService.deleteUserById(id);
	}

}
