package com.searchEngine.searchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchEngine.searchEngine.model.ContactModel;
import com.searchEngine.searchEngine.service.EmailService;
import com.searchEngine.searchEngine.service.RecaptchaService;

@Controller
public class ContactController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private RecaptchaService recaptchaService;
    
    @GetMapping("/contact")
    public String getContact(
        Model model
    ){
        return "contact";
    }

    @PostMapping("/contact")
    public String setContact(
        @ModelAttribute ContactModel contactModel,
        @RequestParam("g-recaptcha-response") String recaptchaResponse,
        Model model
    ) throws Exception{
        if(!this.recaptchaService.validateRecaptcha(recaptchaResponse))
            throw new Exception("Recaptcha lack of validation");
        
        this.emailService.sendContactEmail(contactModel);

        return "contact";
    }
}
