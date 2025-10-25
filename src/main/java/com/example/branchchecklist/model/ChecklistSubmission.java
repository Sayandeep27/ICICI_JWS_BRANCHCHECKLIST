package com.example.branchchecklist.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="checklist_submissions")
public class ChecklistSubmission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="branch_id", nullable=false)
    private Long branchId;

    @Column(name="section_name", nullable=false)
    private String sectionName;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBranchId() { return branchId; }
    public void setBranchId(Long branchId) { this.branchId = branchId; }
    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) {
        this.items.clear();
        if (items != null) {
            items.forEach(i -> i.setSubmission(this));
            this.items.addAll(items);
        }
    }

    @Entity
    @Table(name="checklist_submission_items")
    public static class Item {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="submission_id", nullable=false)
        private ChecklistSubmission submission;

        private String title;
        private String status;

        @Column(name="review_text", columnDefinition = "NVARCHAR(MAX)")
        private String reviewText;

        @Column(name="image_base64", columnDefinition = "NVARCHAR(MAX)")
        private String imageBase64;

        public Long getId() { return id; }
        public ChecklistSubmission getSubmission() { return submission; }
        public void setSubmission(ChecklistSubmission submission) { this.submission = submission; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getReviewText() { return reviewText; }
        public void setReviewText(String reviewText) { this.reviewText = reviewText; }
        public String getImageBase64() { return imageBase64; }
        public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }
    }
}
