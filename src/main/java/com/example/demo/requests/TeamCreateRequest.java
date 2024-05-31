package com.example.demo.requests;

import lombok.Data;

@Data
public class TeamCreateRequest {
    Long id;
    Long coach_id;
    Long client_id;
}
