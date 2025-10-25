package com.example.branchchecklist.controller;

import com.example.branchchecklist.model.Branch;
import com.example.branchchecklist.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) { this.branchService = branchService; }

    @GetMapping("/branch")
    public String branchPage(@RequestParam(required = false) String token, Model model) {
        List<Branch> branches = branchService.getAll();
        model.addAttribute("branches", branches);
        model.addAttribute("token", token);
        return "branch";
    }

    @GetMapping("/api/branches")
    @ResponseBody
    public List<Branch> getAllBranches() { return branchService.getAll(); }

    @GetMapping("/api/branch/{id}")
    @ResponseBody
    public Branch getBranch(@PathVariable Long id) { return branchService.getById(id); }

    @GetMapping("/api/branches/sol/{branchName}")
    @ResponseBody
    public ResponseEntity<?> getSolIdByBranchName(@PathVariable String branchName) {
        Branch branch = branchService.getByBranchName(branchName);
        if (branch != null) return ResponseEntity.ok(branch.getSolId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch not found for name: " + branchName);
    }
}
