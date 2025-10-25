package com.example.branchchecklist.service;

import com.example.branchchecklist.model.ChecklistSubmission;
import com.example.branchchecklist.repository.ChecklistSubmissionRepository;
import org.springframework.stereotype.Service;

@Service
public class ChecklistSubmissionService {

    private final ChecklistSubmissionRepository repository;

    public ChecklistSubmissionService(ChecklistSubmissionRepository repository) {
        this.repository = repository;
    }

    public ChecklistSubmission saveChecklist(ChecklistSubmission checklist) {
        if (checklist.getItems() != null) {
            checklist.getItems().forEach(i -> i.setSubmission(checklist));
        }
        return repository.save(checklist);
    }

    public ChecklistSubmission getChecklistByBranch(Long branchId) {
        return repository.findByBranchId(branchId).orElse(null);
    }

    public Iterable<ChecklistSubmission> getAllChecklists() {
        return repository.findAll();
    }
}
