package com.example.demo.requests;

import lombok.Data;

@Data
public class CoachCreateRequest {
    Long id;
    String profession;
    String experience;
    Long user_id;
}
