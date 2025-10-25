package com.example.branchchecklist.repository;

import com.example.branchchecklist.model.ChecklistCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<ChecklistCategory, Long> {
    Optional<ChecklistCategory> findBySection(String section);
}
