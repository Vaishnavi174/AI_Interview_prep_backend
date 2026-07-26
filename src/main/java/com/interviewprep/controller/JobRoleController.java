package com.interviewprep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meta")
public class JobRoleController {

    @GetMapping("/job-roles")
    public List<String> jobRoles() {
        return List.of(
                "Backend Developer", "Frontend Developer", "Full Stack Developer",
                "Java Developer", "Data Analyst", "Data Scientist", "DevOps Engineer",
                "QA / SDET", "Mobile Developer (Android)", "Machine Learning Engineer",
                "Product Manager", "Cloud Engineer"
        );
    }

    @GetMapping("/company-types")
    public List<String> companyTypes() {
        return List.of("Product-based", "Service-based", "Startup", "MNC", "FAANG-tier");
    }

    @GetMapping("/experience-levels")
    public List<String> experienceLevels() {
        return List.of("Fresher", "0-1 yrs", "1-3 yrs", "3-5 yrs", "5+ yrs");
    }

    @GetMapping("/aptitude-topics")
    public List<String> aptitudeTopics() {
        return List.of("Quantitative", "Logical Reasoning", "Verbal Ability", "Data Interpretation");
    }

    @GetMapping("/dsa-topics")
    public List<String> dsaTopics() {
        return List.of("Arrays", "Strings", "Linked List", "Stacks & Queues", "Trees",
                "Graphs", "Dynamic Programming", "Recursion & Backtracking", "Sorting & Searching");
    }

    @GetMapping("/java-topics")
    public List<String> javaTopics() {
        return List.of("Core Java Basics", "OOP", "Collections", "Exception Handling",
                "Multithreading", "Streams & Lambdas", "JVM Internals", "Spring Boot Basics");
    }
}
