package com.example.demo.requests;

import lombok.Data;

@Data
public class NutritionCreateRequest {
    Long id;
    Long coach_id;
    Long client_id;
    String target;
    String meal;
    int calori;
}
