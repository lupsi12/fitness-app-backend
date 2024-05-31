package com.example.demo.requests;


import lombok.Data;
@Data
public class MessageCreateRequest {
    Long id;
    String send;
    String messagetext;
    Long client_id;
    Long coach_id;
}
