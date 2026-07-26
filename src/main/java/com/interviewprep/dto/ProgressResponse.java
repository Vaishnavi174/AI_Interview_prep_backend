package com.interviewprep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ProgressResponse {

    private int totalAttempted;
    private int totalCorrect;
    private double accuracyPercent;

    private Map<String, Integer> attemptedByCategory;
    private Map<String, Integer> correctByCategory;
    private Map<String, Double> accuracyByDifficulty;

    private int mockInterviewsCompleted;
    private int resumeAnalysesRun;

    // NEW
    private Integer latestInterviewScore;
    private String latestInterviewFeedback;

}