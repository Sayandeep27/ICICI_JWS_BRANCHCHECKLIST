package com.example.branchchecklist.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "checklist_submissions")
public class ChecklistSubmission {

    @Id
    @Column(length = 50)
    private String id;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "section_name")
    private String sectionName; // e.g., Outside, Inside, ATM Lobby

    @OneToMany(mappedBy = "checklistSubmission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    // ===== Inner Class as JPA Entity =====
    @Entity
    @Table(name = "checklist_items")
    public static class Item {

        @Id
        @Column(length = 50)
        private String id;

        private String title;
        private String status;
        private String reviewText;

        @Column(columnDefinition = "NVARCHAR(MAX)")
        private String imageBase64;

        @ManyToOne
        @JoinColumn(name = "checklist_submission_id")
        private ChecklistSubmission checklistSubmission;

        // Getters & Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getReviewText() { return reviewText; }
        public void setReviewText(String reviewText) { this.reviewText = reviewText; }

        public String getImageBase64() { return imageBase64; }
        public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }

        public ChecklistSubmission getChecklistSubmission() { return checklistSubmission; }
        public void setChecklistSubmission(ChecklistSubmission checklistSubmission) { this.checklistSubmission = checklistSubmission; }
    }

    // ===== Getters & Setters =====
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBranchId() { return branchId; }
    public void setBranchId(String branchId) { this.branchId = branchId; }

    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}
