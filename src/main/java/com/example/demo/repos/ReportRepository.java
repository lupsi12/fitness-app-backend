package com.example.demo.repos;

import com.example.demo.entities.report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<report,Long> {
    List<report> findByClientIdAndCoachId(Long clientId, Long coachId);

    List<report> findByClientId(Long clientId);

    List<report> findByCoachId(Long coachId);
}
