package com.mangokiwi.service;

import com.mangokiwi.model.User;
import com.mangokiwi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by zhenfeng on 4/27/17.
 */

@Service
public class UserService implements UserDetailsService {


    public UserRepository userRepository;

    @Autowired
    public void SetUserRepository( UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findByUserName(String name){
        return userRepository.findByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name);
        if(user == null){
            throw new UsernameNotFoundException("User not found with name "+ name);
        }
        return user;
    }

}
