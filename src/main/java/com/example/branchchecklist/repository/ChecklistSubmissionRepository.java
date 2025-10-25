package com.example.branchchecklist.repository;

import com.example.branchchecklist.model.ChecklistSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChecklistSubmissionRepository extends JpaRepository<ChecklistSubmission, Long> {
    Optional<ChecklistSubmission> findByBranchId(Long branchId);
}
