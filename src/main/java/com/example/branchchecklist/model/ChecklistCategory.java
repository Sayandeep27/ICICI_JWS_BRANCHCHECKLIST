package com.example.branchchecklist.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "checklist_categories")
public class ChecklistCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String section; // Outside | Inside | ATM Lobby

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChecklistCategoryItem> categories = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public List<ChecklistCategoryItem> getCategories() { return categories; }
    public void setCategories(List<ChecklistCategoryItem> categories) { this.categories = categories; }
}
