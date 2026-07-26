package com.interviewprep.dto;

import lombok.Data;

@Data
public class AnswerSubmitRequest {
    private Long questionId;
    private String selectedOption; // "A"/"B"/"C"/"D"
}
