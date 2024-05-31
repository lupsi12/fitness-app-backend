package com.example.demo.repos;

import com.example.demo.entities.programs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramsRepository extends JpaRepository<programs,Long> {

    List<programs> findByClientIdAndCoachId(Long clientId, Long coachId);

    List<programs> findByClientId(Long clientId);

    List<programs> findByCoachId(Long coachId);
}
