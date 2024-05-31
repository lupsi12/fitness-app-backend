package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="team")
public class team {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch =  FetchType.EAGER)//lazy coach objeai gelmesin diye
    @JoinColumn(name = "coach_id",nullable = false)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    coach coach;
    @OneToOne(fetch =  FetchType.EAGER)//lazy coach objeai gelmesin diye
    @JoinColumn(name = "client_id",nullable = false)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    client client;
}
