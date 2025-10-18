package com.example.branchchecklist.service;

import com.example.branchchecklist.model.Branch;
import com.example.branchchecklist.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // Fetch all branches
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    // Fetch a branch by ID
    public Branch getById(String id) {
        return branchRepository.findById(id).orElse(null);
    }

    // Fetch a branch by its name
    public Branch getByBranchName(String branchName) {
        return branchRepository.findByBranchName(branchName).orElse(null);
    }

    // Save or update a branch
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }
}
