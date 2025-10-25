package com.example.branchchecklist.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="branch_id", nullable=false)
    private Long branchId;

    @Column(name="branch_name")
    private String branchName;

    @Column(name="sol_id")
    private String solId;

    private String section;
    private String category;

    @Column(name="sub_item")
    private String subItem;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String remarks;

    @Column(name="photo_base64", columnDefinition = "NVARCHAR(MAX)")
    private String photoBase64;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Long getBranchId() { return branchId; }
    public void setBranchId(Long branchId) { this.branchId = branchId; }
    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
    public String getSolId() { return solId; }
    public void setSolId(String solId) { this.solId = solId; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getSubItem() { return subItem; }
    public void setSubItem(String subItem) { this.subItem = subItem; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public String getPhotoBase64() { return photoBase64; }
    public void setPhotoBase64(String photoBase64) { this.photoBase64 = photoBase64; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
