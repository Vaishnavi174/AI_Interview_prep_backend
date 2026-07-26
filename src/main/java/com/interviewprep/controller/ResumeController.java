package com.interviewprep.controller;

import com.interviewprep.dto.ResumeAnalyzeRequest;
import com.interviewprep.entity.ResumeAnalysis;
import com.interviewprep.repository.ResumeAnalysisRepository;
import com.interviewprep.service.ResumeAnalyzerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeAnalyzerService resumeAnalyzerService;
    private final ResumeAnalysisRepository resumeAnalysisRepository;

    @PostMapping("/analyze")
    public ResponseEntity<ResumeAnalysis> analyze(HttpServletRequest request, @Valid @RequestBody ResumeAnalyzeRequest req) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(resumeAnalyzerService.analyze(userId, req));
    }

    @GetMapping("/history")
    public ResponseEntity<List<ResumeAnalysis>> history(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(resumeAnalysisRepository.findByUserIdOrderByCreatedAtDesc(userId));
    }
}
