package com.mangokiwi.service;

import com.mangokiwi.core.annotation.HandleEntityNotFound;
import com.mangokiwi.core.exception.EntityNotFoundException;
import com.mangokiwi.model.TeacherStatus;
import com.mangokiwi.model.Token;
import com.mangokiwi.model.User;
import com.mangokiwi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zhenfeng on 4/27/17.
 */

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
	private TokenService tokenService;

    @HandleEntityNotFound
    public void validateUserById(Long id){
        userRepository.findById(id);
    }

    @HandleEntityNotFound
    public User getUserByName(String name){
        User result = userRepository.findByUsername(name);
        return result;
    }

    @HandleEntityNotFound
    public User getUserById(Long id){
        User result = userRepository.findById(id);
        return result;
    }

    @HandleEntityNotFound
	public User getUserByAccessTokenAndVendor(String accessToken, String vendor){
    	Token token = tokenService.getTokenByAccessTokenAndVendor(accessToken, vendor);
    	return getUserById(token.getUserId());
	}

	public void updateTeacherStatus(Long id, TeacherStatus status){
	    User user = getUserById(id);
	    user.setTeacherStatus(status);
	    userRepository.save(user);
    }

    // when using update , user's id must not be null and exist in database
    public User update(User user){
        return userRepository.save(user);
    }

    // when using add , user's id should be null and new one will be created
    public User add(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


}
