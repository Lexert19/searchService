package com.searchEngine.searchEngine.model;

import java.time.LocalDate;

import com.searchEngine.searchEngine.staticClass.Validator;

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
        if(!Validator.isValidEmail(email))
            throw new IllegalArgumentException("Incorrect E-mail!");

        this.email = email;
    }

   

    public void setBirthDate(String birthDate){
        this.birthDate = LocalDate.parse(birthDate);
    }
    

}
