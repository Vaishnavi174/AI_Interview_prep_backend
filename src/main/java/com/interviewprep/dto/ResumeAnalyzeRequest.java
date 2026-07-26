package com.interviewprep.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResumeAnalyzeRequest {
    @NotBlank
    private String resumeText;

    private String targetJobRole; // optional override, otherwise uses user's profile setting
}
