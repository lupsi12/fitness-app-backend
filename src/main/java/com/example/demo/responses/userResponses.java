package com.example.demo.responses;

import com.example.demo.entities.user;
import lombok.Data;

import java.util.Date;

@Data
public class userResponses {
    Long id;
    String email;
    String password;
    String name;
    String surname;
    Date dateofbirth;
    Date dateofregistration;
    String gender;
    String telephonenumber;
    String tur;
    public  userResponses(user entity){
        this.id = entity.getId();
        this.dateofbirth = entity.getDateofbirth();
        this.dateofregistration = entity.getDateofregistration();
        this.email = entity.getEmail();
        this.gender = entity.getGender();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.password = entity.getPassword();
        this.tur = entity.getTur();
        this.telephonenumber = entity.getTelephonenumber();
    }
}
