package com.searchEngine.searchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.searchEngine.searchEngine.model.ContactModel;
import com.searchEngine.searchEngine.service.EmailService;

@Controller
public class ContactController {
    @Autowired
    private EmailService emailService;
    
    @GetMapping("/contact")
    public String getContact(
        Model model
    ){
        return "contact";
    }

    @PostMapping("/contact")
    public String setContact(
        @ModelAttribute ContactModel contactModel,
        Model model
    ){
        this.emailService.sendContactEmail(contactModel);

        return "contact";
    }
}
