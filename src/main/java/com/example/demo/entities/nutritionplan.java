package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="nutritionplan")
public class nutritionplan {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch =  FetchType.EAGER)//lazy coach objeai gelmesin diye
    @JoinColumn(name = "coach_id",nullable = false)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    coach coach;
    @ManyToOne(fetch =  FetchType.EAGER)//lazy coach objeai gelmesin diye
    @JoinColumn(name = "client_id",nullable = false)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    client client;
    String target;
    String meal;
    int calori;

}
