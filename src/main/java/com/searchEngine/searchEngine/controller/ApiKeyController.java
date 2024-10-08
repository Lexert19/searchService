package com.searchEngine.searchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.service.ApiKeyService;

@Controller
@RequestMapping("/key")
public class ApiKeyController {
    @Autowired
    private ApiKeyService apiKeyService;
    
    @PostMapping("/register")
    public String registerApiKey(
        Model model,
        @AuthenticationPrincipal User user

    ){
        String key = apiKeyService.createApiKey(user).getKey();
        model.addAttribute("key", key);
        return "user/registerKey";
    }

    @GetMapping("/register")
    public String registerApiKeyForm(
        Model model
    ){
        return "user/registerKeyForm";
    }


}
