package com.example.demo.requests;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
	
	String email;
	String password;
	String name;
	String surname;
	Date dateofbirth;
	Date dateofregistration;
	String gender;
	String telephonenumber;

}
