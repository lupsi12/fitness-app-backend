package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(name="coach")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class coach {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    String profession;
    String experience;
    @OneToOne(fetch =  FetchType.EAGER)//lazy user objeai gelmesin diye
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    user user;
}
