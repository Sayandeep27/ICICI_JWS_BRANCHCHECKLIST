package com.example.branchchecklist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "checklist_subitems")
public class ChecklistSubItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", nullable=false)
    private ChecklistCategoryItem item;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ChecklistCategoryItem getItem() { return item; }
    public void setItem(ChecklistCategoryItem item) { this.item = item; }
}
