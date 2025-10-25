package com.example.branchchecklist.controller;

import com.example.branchchecklist.model.ChecklistCategory;
import com.example.branchchecklist.dto.ChecklistResponseDto;
import com.example.branchchecklist.model.Review;
import com.example.branchchecklist.service.ChecklistService;
import com.example.branchchecklist.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistController {

    private final ChecklistService checklistService;
    private final ReviewService reviewService;

    public ChecklistController(ChecklistService checklistService, ReviewService reviewService) {
        this.checklistService = checklistService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{section}")
    public ChecklistResponseDto getChecklist(@PathVariable String section) {
    return checklistService.getChecklistData(section);
   }

    @PostMapping("/review")
    public Review submitReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }
}
