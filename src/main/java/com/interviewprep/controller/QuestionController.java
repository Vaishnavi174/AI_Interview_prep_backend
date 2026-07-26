package com.interviewprep.controller;

import com.interviewprep.dto.AnswerResultResponse;
import com.interviewprep.dto.AnswerSubmitRequest;
import com.interviewprep.dto.QuestionPublicResponse;
import com.interviewprep.entity.Difficulty;
import com.interviewprep.entity.QuestionCategory;
import com.interviewprep.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    // e.g. GET /api/questions?category=JAVA&difficulty=EASY
    // e.g. GET /api/questions?category=APTITUDE&topic=Quantitative
    @GetMapping
    public ResponseEntity<List<QuestionPublicResponse>> getQuestions(
            @RequestParam QuestionCategory category,
            @RequestParam(required = false) Difficulty difficulty,
            @RequestParam(required = false) String topic
    ) {
        return ResponseEntity.ok(questionService.getQuestions(category, difficulty, topic));
    }

    @PostMapping("/answer")
    public ResponseEntity<AnswerResultResponse> submitAnswer(HttpServletRequest request, @RequestBody AnswerSubmitRequest req) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(questionService.submitAnswer(userId, req));
    }
}
