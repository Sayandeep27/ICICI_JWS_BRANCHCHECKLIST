package com.example.branchchecklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BranchChecklistApplication {
    public static void main(String[] args) {
        SpringApplication.run(BranchChecklistApplication.class, args);
        System.out.println("✅ Branch Checklist Application running on http://localhost:8080");
    }
}
