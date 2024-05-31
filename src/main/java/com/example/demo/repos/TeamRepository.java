package com.example.demo.repos;

import com.example.demo.entities.team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<team,Long> {
    List<team> findByClientIdAndCoachId(Long clientId, Long coachId);

    List<team> findByClientId(Long clientId);

    List<team> findByCoachId(Long coachId);
}
