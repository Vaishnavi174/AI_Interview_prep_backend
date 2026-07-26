package com.interviewprep.service;

import com.interviewprep.dto.AnswerResultResponse;
import com.interviewprep.dto.AnswerSubmitRequest;
import com.interviewprep.dto.QuestionPublicResponse;
import com.interviewprep.entity.*;
import com.interviewprep.repository.QuestionRepository;
import com.interviewprep.repository.UserAnswerRepository;
import com.interviewprep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final UserRepository userRepository;

    public List<QuestionPublicResponse> getQuestions(QuestionCategory category, Difficulty difficulty, String topic) {
        List<Question> questions;
        if (difficulty != null && topic != null) {
            questions = questionRepository.findByCategoryAndDifficultyAndTopic(category, difficulty, topic);
        } else if (difficulty != null) {
            questions = questionRepository.findByCategoryAndDifficulty(category, difficulty);
        } else if (topic != null) {
            questions = questionRepository.findByCategoryAndTopic(category, topic);
        } else {
            questions = questionRepository.findByCategory(category);
        }
        return questions.stream().map(QuestionPublicResponse::from).collect(Collectors.toList());
    }

    public AnswerResultResponse submitAnswer(Long userId, AnswerSubmitRequest req) {
        Question question = questionRepository.findById(req.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        boolean correct = question.getCorrectOption() != null &&
                question.getCorrectOption().equalsIgnoreCase(req.getSelectedOption());

        UserAnswer answer = UserAnswer.builder()
                .user(userRepository.getReferenceById(userId))
                .question(question)
                .selectedOption(req.getSelectedOption())
                .correct(correct)
                .build();
        userAnswerRepository.save(answer);

        return new AnswerResultResponse(correct, question.getCorrectOption(), question.getExplanation());
    }
}
