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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="message")
public class message {
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
    String send;
    @Column(columnDefinition="text")
    String messagetext;
}
