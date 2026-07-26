package com.interviewprep.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MockInterviewReplyRequest {
    @NotBlank
    private String message;
}
