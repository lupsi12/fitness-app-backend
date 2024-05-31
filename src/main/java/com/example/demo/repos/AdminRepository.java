package com.example.demo.repos;

import com.example.demo.entities.admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<admin,Long> {

    List<admin> findByUserId(Long userId);
}
