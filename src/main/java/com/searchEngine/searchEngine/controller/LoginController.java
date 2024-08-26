package com.searchEngine.searchEngine.controller;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.searchEngine.searchEngine.model.UserModel;
import com.searchEngine.searchEngine.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "user/login";
    }

    @PostMapping("/login")
    public String postLogin(
        @ModelAttribute UserModel userModel,
        HttpServletResponse response,
        Model model) throws AuthenticationException {

        response.addCookie(authService.authenticate(userModel));

        return "user/login";
    }
    
}
