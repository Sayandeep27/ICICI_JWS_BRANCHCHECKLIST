package com.example.branchchecklist.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "checklist_entries")
public class ChecklistEntry {

    @Id
    @Column(length = 50)
    private String id;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String mobileEncrypted;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String branchIdEncrypted;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String branchNameEncrypted;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String solIdEncrypted;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String sectionEncrypted;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String itemEncrypted;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String remarksEncrypted;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String imageEncrypted;

    private Instant createdAt;

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMobileEncrypted() { return mobileEncrypted; }
    public void setMobileEncrypted(String mobileEncrypted) { this.mobileEncrypted = mobileEncrypted; }

    public String getBranchIdEncrypted() { return branchIdEncrypted; }
    public void setBranchIdEncrypted(String branchIdEncrypted) { this.branchIdEncrypted = branchIdEncrypted; }

    public String getBranchNameEncrypted() { return branchNameEncrypted; }
    public void setBranchNameEncrypted(String branchNameEncrypted) { this.branchNameEncrypted = branchNameEncrypted; }

    public String getSolIdEncrypted() { return solIdEncrypted; }
    public void setSolIdEncrypted(String solIdEncrypted) { this.solIdEncrypted = solIdEncrypted; }

    public String getSectionEncrypted() { return sectionEncrypted; }
    public void setSectionEncrypted(String sectionEncrypted) { this.sectionEncrypted = sectionEncrypted; }

    public String getItemEncrypted() { return itemEncrypted; }
    public void setItemEncrypted(String itemEncrypted) { this.itemEncrypted = itemEncrypted; }

    public String getRemarksEncrypted() { return remarksEncrypted; }
    public void setRemarksEncrypted(String remarksEncrypted) { this.remarksEncrypted = remarksEncrypted; }

    public String getImageEncrypted() { return imageEncrypted; }
    public void setImageEncrypted(String imageEncrypted) { this.imageEncrypted = imageEncrypted; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
