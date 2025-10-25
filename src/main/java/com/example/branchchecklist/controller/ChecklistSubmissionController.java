package com.example.branchchecklist.controller;

import com.example.branchchecklist.model.ChecklistSubmission;
import com.example.branchchecklist.service.ChecklistSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/checklist-section")
public class ChecklistSubmissionController {

    @Autowired
    private ChecklistSubmissionService service;

    @PostMapping("/submit")
    public Map<String, Object> submitChecklist(@RequestBody ChecklistSubmission checklist) {
        ChecklistSubmission saved = service.saveChecklist(checklist);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "Checklist submitted successfully");
        res.put("id", saved.getId());
        return res;
    }

    @GetMapping("/{branchId}")
    public ChecklistSubmission getChecklistByBranch(@PathVariable Long branchId) {
        return service.getChecklistByBranch(branchId);
    }

    @GetMapping("/all")
    public Iterable<ChecklistSubmission> getAllChecklists() {
        return service.getAllChecklists();
    }
}
