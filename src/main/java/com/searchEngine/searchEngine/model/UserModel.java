package com.searchEngine.searchEngine.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private String password;
    private String username;
    private String email;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;

    public void setEmail(String email){
        if(!isValidEmail(email))
            throw new IllegalArgumentException("Incorrect E-mail!");

        this.email = email;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    public void setBirthDate(String birthDate){
        this.birthDate = LocalDate.parse(birthDate);
    }
    

}
