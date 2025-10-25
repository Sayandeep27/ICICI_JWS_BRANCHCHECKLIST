package com.example.branchchecklist.service;

import com.example.branchchecklist.model.Branch;
import com.example.branchchecklist.model.Review;
import com.example.branchchecklist.repository.BranchRepository;
import com.example.branchchecklist.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BranchRepository branchRepository;

    public ReviewService(ReviewRepository reviewRepository, BranchRepository branchRepository) {
        this.reviewRepository = reviewRepository;
        this.branchRepository = branchRepository;
    }

    public Review saveReview(Review review) {
        Branch branch = branchRepository.findById(review.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid branchId: Branch not found"));
        if (review.getBranchName() == null || review.getBranchName().isEmpty()) {
            review.setBranchName(branch.getBranchName());
        }
        if (review.getSolId() == null || review.getSolId().isEmpty()) {
            review.setSolId(branch.getSolId());
        }
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByBranch(Long branchId) {
        return reviewRepository.findByBranchId(branchId);
    }

    public List<Review> getAllReviews() { return reviewRepository.findAll(); }

    public void deleteReview(Long reviewId) { reviewRepository.deleteById(reviewId); }
}
