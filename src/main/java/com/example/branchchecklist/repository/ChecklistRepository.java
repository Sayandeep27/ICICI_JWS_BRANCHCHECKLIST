package com.example.branchchecklist.repository;

import com.example.branchchecklist.model.ChecklistCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<ChecklistCategory, String> {
    ChecklistCategory findBySection(String section);
}
