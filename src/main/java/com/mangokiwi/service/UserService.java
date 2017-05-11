package com.mangokiwi.service;

import com.mangokiwi.core.annotation.HandleEntityNotFound;
import com.mangokiwi.model.Token;
import com.mangokiwi.model.User;
import com.mangokiwi.repository.TokenRepository;
import com.mangokiwi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Created by zhenfeng on 4/27/17.
 */

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
	private TokenService tokenService;

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
    public User getUserByToken(Token token){
        return token.getUser();
    }

    @HandleEntityNotFound
	public User getUserByAccessTokenAndVendor(String accessToken, String vendor){
    	Token token = tokenService.getTokenByAccessTokenAndVendor(accessToken, vendor);
    	return getUserByToken(token);
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
