package com.searchEngine.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.UserModel;
import com.searchEngine.searchEngine.repository.UserRepository;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserModelService userModelService;

    public User registerUser(UserModel userModel) throws Exception{
        if(doesUserExists(userModel.getEmail()))
            throw new Exception("User already exists!");

        User user = userModelService.createUser(userModel);
        userRepository.save(user);

        return user;
    }

    public boolean doesUserExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
