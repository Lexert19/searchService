package com.searchEngine.searchEngine.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.model.UserModel;

import jakarta.servlet.http.Cookie;

@Service
public class AuthService {
    public boolean validateUser(String username, String password){
        return "user".equals(username) && "password".equals(password);

    }

    public Cookie authenticate(UserModel userModel) throws AuthenticationException{
        if(validateUser(userModel.getUsername(), userModel.getPassword())){
            return createLoginCookie(userModel.getUsername());
        }
        throw new AuthenticationException("Nie prawidłowe dane logowania");
    }

    public Cookie createLoginCookie(String username){
        Cookie cookie = new Cookie("userSession", username);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24);

        return cookie;
    }
}
