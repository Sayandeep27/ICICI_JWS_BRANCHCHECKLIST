package com.example.branchchecklist.repository;

import com.example.branchchecklist.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByBranchId(Integer branchId); // ✅ Integer
}
