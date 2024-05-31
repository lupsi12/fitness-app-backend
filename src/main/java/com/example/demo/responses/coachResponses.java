package com.example.demo.responses;

import com.example.demo.entities.coach;
import lombok.Data;

@Data
public class coachResponses {
    Long id;
    String experience;
    String profession;
    Long userId;
    String userTelephone;
    String userGender;

    public coachResponses(coach entity){
        this.id = entity.getId();
        this.experience = entity.getExperience();
        this.profession = entity.getProfession();
        this.userId = entity.getUser().getId();
        this.userTelephone = entity.getUser().getTelephonenumber();
        this.userGender = entity.getUser().getGender();
    }
}
