package com.example.branchchecklist.repository;

import com.example.branchchecklist.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBranchId(Long branchId);
}
