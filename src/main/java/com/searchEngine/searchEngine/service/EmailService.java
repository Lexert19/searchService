package com.searchEngine.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.model.ContactModel;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailHost;

    public void sendContactEmail(ContactModel contactModel){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailHost);
        message.setSubject("Contact form");
        message.setText(contactModel.getContent());
        message.setFrom(mailHost);
        message.setReplyTo(contactModel.getEmail());
        mailSender.send(message);
    }

}
