package com.example.branchchecklist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "BranchId")
    private Integer branchId;  // ✅ Integer, not String

    @Column(name = "ReviewedBy")
    private String reviewedBy;

    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "Status")
    private String status;

    public Review() {}

    // Constructor
    public Review(Integer branchId, String reviewedBy, String remarks, String status) {
        this.branchId = branchId;
        this.reviewedBy = reviewedBy;
        this.remarks = remarks;
        this.status = status;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
