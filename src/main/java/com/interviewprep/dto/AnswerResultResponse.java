package com.interviewprep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerResultResponse {
    private boolean correct;
    private String correctOption;
    private String explanation;
}
