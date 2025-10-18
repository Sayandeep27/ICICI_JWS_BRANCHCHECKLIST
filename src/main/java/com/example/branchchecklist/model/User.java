package com.example.branchchecklist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ FIX: Auto-generate primary key
    private Integer id;

    @Column(unique = true, nullable = false)
    private String mobile;

    // Constructors
    public User() {}

    public User(String mobile) {
        this.mobile = mobile;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
