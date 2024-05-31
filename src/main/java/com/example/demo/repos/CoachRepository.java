package com.example.demo.repos;

import com.example.demo.entities.coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends JpaRepository<coach,Long> {
    List<coach> findByUserId(Optional<Long> userId);
}
