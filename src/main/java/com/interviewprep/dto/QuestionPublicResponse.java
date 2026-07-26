package com.interviewprep.dto;

import com.interviewprep.entity.Difficulty;
import com.interviewprep.entity.Question;
import com.interviewprep.entity.QuestionCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Question shape sent to the client BEFORE they answer.
 * Deliberately omits correctOption and explanation so the answer can't be read from the network tab.
 * These are only revealed via /api/questions/answer once the user submits a choice.
 */
@Data
@AllArgsConstructor
public class QuestionPublicResponse {
    private Long id;
    private QuestionCategory category;
    private Difficulty difficulty;
    private String topic;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    public static QuestionPublicResponse from(Question q) {
        return new QuestionPublicResponse(
                q.getId(), q.getCategory(), q.getDifficulty(), q.getTopic(), q.getQuestionText(),
                q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()
        );
    }
}
