package com.example.demo.responses;

import com.example.demo.entities.nutritionplan;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NutritionplanResponses {
    Long id;
    String target;
    String meal;
    int calori;
    Long clientId;
    Long coachId;
    public  NutritionplanResponses(nutritionplan entity){
        this.id = entity.getId();
        this.target = entity.getTarget();
        this.meal = entity.getMeal();
        this.calori = entity.getCalori();
        this.clientId = entity.getClient().getId();
        this.coachId = entity.getCoach().getId();
    }
}
