package com.example.branchchecklist.repository;

import com.example.branchchecklist.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {
    Optional<Branch> findByBranchName(String branchName);
}
