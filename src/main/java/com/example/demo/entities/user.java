package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_")
public class user {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String password;
    @Temporal(TemporalType.DATE)
    Date dateofbirth;
    @Temporal (TemporalType.DATE)
    Date dateofregistration;
    String gender;
    String email;
    String telephonenumber;
    String tur;
}
