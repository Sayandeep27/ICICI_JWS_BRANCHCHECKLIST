package com.example.branchchecklist.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(length = 50)
    private String id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "checklist_category_id")
    private ChecklistCategory checklistCategory;

    @ElementCollection
    @CollectionTable(name = "subitems", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "subitem_text")
    private List<String> subItems;

    // Getters & Setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public ChecklistCategory getChecklistCategory() { return checklistCategory; }
    public void setChecklistCategory(ChecklistCategory checklistCategory) { this.checklistCategory = checklistCategory; }
    public List<String> getSubItems() { return subItems; }
    public void setSubItems(List<String> subItems) { this.subItems = subItems; }
}
