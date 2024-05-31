package com.example.demo.responses;

import com.example.demo.entities.message;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class messageResponses {
    Long id;
    String send;
    @Column(columnDefinition="text")
    String messagetext;
    Long coachId;
    Long clientId;
    public messageResponses(message entity){
        this.id = entity.getId();
        this.send = entity.getSend();
        this.messagetext = entity.getMessagetext();
        this.clientId = entity.getClient().getId();
        this.coachId = entity.getCoach().getId();
    }
}
