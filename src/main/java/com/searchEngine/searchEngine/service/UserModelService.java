package com.searchEngine.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.UserModel;

@Service
public class UserModelService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User createUser(UserModel userModel){
        User user = new User();
        user.setBirthDate(userModel.getBirthDate());
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        user.setPassword(encodedPassword);
        user.setUsername(userModel.getUsername());

        return user;
    }
}
