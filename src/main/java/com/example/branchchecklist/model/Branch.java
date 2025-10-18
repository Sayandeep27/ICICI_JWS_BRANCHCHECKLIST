package com.example.branchchecklist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "BranchName")   // ✅ match your SQL column
    private String branchName;

    @Column(name = "SolId")        // ✅ match your SQL column
    private String solId;

    public Branch() {}

    public Branch(String branchName, String solId) {
        this.branchName = branchName;
        this.solId = solId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }
}
