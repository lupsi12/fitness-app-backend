package com.example.demo.requests;

import lombok.Data;

@Data
public class ReportCreateRequest {
    Long id;
    Long coach_id;
    Long client_id;
    Long weight;
    Long height;
    Long bodyfatradio;
    Long musclemass;
    Long bodymassindex;
}
