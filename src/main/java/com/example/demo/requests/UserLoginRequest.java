package com.example.demo.requests;

import lombok.Data;

@Data
public class UserLoginRequest {
    String email;
    String password;
}
