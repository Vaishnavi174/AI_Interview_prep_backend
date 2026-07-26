package com.interviewprep.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OnboardingRequest {
    @NotBlank
    private String targetJobRole;

    private String targetCompanyType;

    @NotNull
    private Double targetLpa;

    @NotBlank
    private String experienceLevel;
}
