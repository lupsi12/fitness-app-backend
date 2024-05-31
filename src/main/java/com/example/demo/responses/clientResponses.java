package com.example.demo.responses;

import com.example.demo.entities.client;
import lombok.Data;

@Data
public class clientResponses {
    Long id;
    Long userId;
    String userPassword;
    String userEmail;
    public clientResponses(client entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userEmail = entity.getUser().getEmail();
        this.userPassword = entity.getUser().getPassword();
    }
}
