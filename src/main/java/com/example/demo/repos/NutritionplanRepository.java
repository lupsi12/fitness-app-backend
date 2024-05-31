package com.example.demo.repos;

import com.example.demo.entities.nutritionplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NutritionplanRepository extends JpaRepository<nutritionplan,Long> {
    List<nutritionplan> findByClientIdAndCoachId(Long clientId, Long coachId);

    List<nutritionplan> findByClientId(Long clientId);

    List<nutritionplan> findByCoachId(Long coachId);
}
