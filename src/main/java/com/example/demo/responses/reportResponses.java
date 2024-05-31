package com.example.demo.responses;

import com.example.demo.entities.report;
import lombok.Data;

@Data
public class reportResponses {
    Long id;
    Long coachId;
    Long clientId;
    Long weight;
    public reportResponses(report entity){
        this.id = entity.getId();
        this.clientId = entity.getClient().getId();
        this.coachId = entity.getCoach().getId();
        this.weight = entity.getWeight();
    }
}
