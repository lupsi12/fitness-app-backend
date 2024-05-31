package com.example.demo.repos;

import com.example.demo.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<user,Long >{
    List<user> findByEmail(String email);

    List<user> findByTur(String tur);


    Optional<user> findById(Long userId);

    List<user> findByTurAndEmail(String tur, String email);
}