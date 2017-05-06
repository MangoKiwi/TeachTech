package com.mangokiwi.service;

import com.mangokiwi.core.annotation.HandleEntityNotFound;
import com.mangokiwi.model.User;
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

    @HandleEntityNotFound
    public User getUserDetailByName(String name){
        User result = userRepository.findByUsername(name);
        return result;
    }

    @HandleEntityNotFound
    public User getUserDetailById(Long id){
        User result = userRepository.findById(id);
        return result;
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
