package com.example.demo.responses;

import com.example.demo.entities.programs;
import lombok.Data;

@Data
public class programsResponses {
    Long id;
    Long coachId;
    Long clientId;
    int set;
    public programsResponses(programs entity){
        this.id = entity.getId();
        this.coachId = entity.getCoach().getId();
        this.clientId = entity.getClient().getId();
    }
}
