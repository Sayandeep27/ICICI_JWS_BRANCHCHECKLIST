package com.example.branchchecklist.service;

import com.example.branchchecklist.model.ChecklistCategory;
import com.example.branchchecklist.repository.ChecklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }

    // Fetch checklist category by section name
    public ChecklistCategory getChecklistBySection(String section) {
        return checklistRepository.findBySection(section);
    }

    // Fetch all checklist categories
    public List<ChecklistCategory> getAllChecklists() {
        return checklistRepository.findAll();
    }

    // Save a new checklist category
    public ChecklistCategory save(ChecklistCategory checklistCategory) {
        return checklistRepository.save(checklistCategory);
    }
}
