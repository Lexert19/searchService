package com.searchEngine.searchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.searchEngine.searchEngine.model.UserModel;
import com.searchEngine.searchEngine.service.AuthService;
import com.searchEngine.searchEngine.service.RegisterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private RegisterService registerService;

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "user/login";
    }

    @PostMapping("/login")
    public String postLogin(
            @ModelAttribute UserModel userModel,
            HttpServletResponse response,
            HttpSession session,
            Model model) throws Exception {

        try {
            authService.authenticate(userModel, session);
            return "redirect:/panel";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());

        }

        return "user/login";

    }

    @GetMapping("/register")
    public String getRegister(
            Model model) {

        return "user/register";
    }

    @PostMapping("/register")
    public String postRegister(
            @ModelAttribute UserModel userModel,
            Model model) {

        try {
            if (registerService.registerUser(userModel) != null) {
                return "redirect:/panel";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "user/register";
    }

    @GetMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response) {
        this.authService.logout(request, response);
        return "redirect:/";
    }

}
