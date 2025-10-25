package com.example.branchchecklist.dto;

import java.util.List;

public class ChecklistCategoryDto {
    private String title;
    private List<String> subItems;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getSubItems() { return subItems; }
    public void setSubItems(List<String> subItems) { this.subItems = subItems; }
}
