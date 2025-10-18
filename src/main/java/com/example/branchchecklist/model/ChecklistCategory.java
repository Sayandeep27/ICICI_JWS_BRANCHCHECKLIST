package com.example.branchchecklist.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "checklist_categories")
public class ChecklistCategory {

    @Id
    @Column(length = 50)
    private String id;

    private String section;

    @OneToMany(mappedBy = "checklistCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }
}
