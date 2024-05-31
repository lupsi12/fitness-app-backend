package com.example.demo.repos;

import com.example.demo.entities.client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<client,Long> {
    List<client> findByUserId(Optional<Long> userId);
}
