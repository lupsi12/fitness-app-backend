package com.example.demo.repos;

import com.example.demo.entities.message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<message,Long> {
    List<message> findByClientIdAndCoachId(Long clientId, Long coachId);

    List<message> findByClientId(Long clientId);

    List<message> findByCoachId(Long coachId);
}
