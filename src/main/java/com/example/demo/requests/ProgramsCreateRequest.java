package com.example.demo.requests;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class ProgramsCreateRequest {
    Long id;
    Long coach_id;
    Long client_id;
    String egzersize;
    String target;
    int set;
    String videoguides;
    @Temporal(TemporalType.DATE)
    Date programstartdate;
    int programduration;
}
