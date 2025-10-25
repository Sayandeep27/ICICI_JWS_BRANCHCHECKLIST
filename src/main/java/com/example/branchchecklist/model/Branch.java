package com.example.branchchecklist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "branch_name", nullable = false, unique = true)
    private String branchName;

    @Column(name = "sol_id", nullable = false)
    private String solId;

    public Branch() {}
    public Branch(String branchName, String solId) {
        this.branchName = branchName; this.solId = solId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
    public String getSolId() { return solId; }
    public void setSolId(String solId) { this.solId = solId; }
}
