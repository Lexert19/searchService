package com.searchEngine.searchEngine.model;

import com.searchEngine.searchEngine.staticClass.Validator;

import lombok.Getter;

@Getter
public class ContactModel {
    private String email;
    private String firstName;
    private String lastName;
    private String content;
    private boolean termsAndConditions;
    private boolean privacyPolicy;

    public void setPrivacyPolicy(boolean privacyPolicty) throws Exception{
        if(!privacyPolicty)
            throw new Exception("Accept privacy policy!");
        this.privacyPolicy = privacyPolicty;
    }

    public void setEmail(String email) {
        if(!Validator.isValidEmail(email))
            throw new IllegalArgumentException("Incorrect E-mail!");

        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTermsAndConditions(boolean termsAndConditions) throws Exception {
        if(!termsAndConditions)
            throw new Exception("Accept terms and conditioins!");
        this.termsAndConditions = termsAndConditions;
    }

    
}
