package com.example.demo.responses;

import com.example.demo.entities.team;
import lombok.Data;

@Data
public class teamResponses {
    Long id;
    Long coachId;
    Long clientId;
    public teamResponses(team entity){
        this.id = entity.getId();
        this.clientId = entity.getClient().getId();
        this.coachId = entity.getCoach().getId();
    }
}
