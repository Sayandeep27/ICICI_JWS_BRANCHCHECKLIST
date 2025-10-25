package com.example.branchchecklist.dto;

import java.util.List;

public class ChecklistResponseDto {
    private String section;
    private List<ChecklistCategoryDto> categories;

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public List<ChecklistCategoryDto> getCategories() { return categories; }
    public void setCategories(List<ChecklistCategoryDto> categories) { this.categories = categories; }
}
