package com.example.branchchecklist.service;

import com.example.branchchecklist.model.Branch;
import com.example.branchchecklist.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) { this.branchRepository = branchRepository; }

    public List<Branch> getAll() { return branchRepository.findAll(); }

    public Branch getById(Long id) { return branchRepository.findById(id).orElse(null); }

    public Branch getByBranchName(String branchName) {
        return branchRepository.findByBranchName(branchName).orElse(null);
    }
}
