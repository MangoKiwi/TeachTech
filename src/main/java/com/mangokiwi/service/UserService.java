package com.mangokiwi.service;

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

    public User findByUserName(String name){
        return userRepository.findByUsername(name);
    }

    public User findById(Long id){return userRepository.findById(id);}


    public User updateById(Long id, User user){
        return null;
    }

    public User add(User user){
        return null;
    }

    public User deleteById(Long id){
        return null;
    }


}
