package com.example.demo.responses;

import com.example.demo.entities.admin;
import lombok.Data;

@Data
public class adminResponses {
    Long id;
    Long userId;
    String userName;
    String userSurname;
    public adminResponses(admin entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getName();
        this.userSurname = entity.getUser().getSurname();
    }


}
