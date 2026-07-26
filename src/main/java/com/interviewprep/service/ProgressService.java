package com.interviewprep.service;

import com.interviewprep.dto.ProgressResponse;
import com.interviewprep.entity.Difficulty;
import com.interviewprep.entity.UserAnswer;
import com.interviewprep.repository.MockInterviewSessionRepository;
import com.interviewprep.repository.ResumeAnalysisRepository;
import com.interviewprep.repository.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.interviewprep.entity.MockInterviewSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final UserAnswerRepository userAnswerRepository;
    private final MockInterviewSessionRepository mockInterviewSessionRepository;
    private final ResumeAnalysisRepository resumeAnalysisRepository;

    @Transactional(readOnly = true)
    public ProgressResponse getProgress(Long userId) {
        List<UserAnswer> answers = userAnswerRepository.findByUserId(userId);

        int total = answers.size();
        int correct = (int) answers.stream().filter(UserAnswer::isCorrect).count();
        double accuracy = total == 0 ? 0 : (correct * 100.0) / total;

        Map<String, Integer> attemptedByCategory = new HashMap<>();
        Map<String, Integer> correctByCategory = new HashMap<>();
        Map<String, List<UserAnswer>> byDifficulty = new HashMap<>();

        for (UserAnswer a : answers) {
            String cat = a.getQuestion().getCategory().name();
            attemptedByCategory.merge(cat, 1, Integer::sum);
            if (a.isCorrect()) correctByCategory.merge(cat, 1, Integer::sum);

            String diff = a.getQuestion().getDifficulty() != null ? a.getQuestion().getDifficulty().name() : "N/A";
            byDifficulty.computeIfAbsent(diff, k -> new java.util.ArrayList<>()).add(a);
        }

        Map<String, Double> accuracyByDifficulty = new HashMap<>();
        for (Map.Entry<String, List<UserAnswer>> e : byDifficulty.entrySet()) {
            long c = e.getValue().stream().filter(UserAnswer::isCorrect).count();
            accuracyByDifficulty.put(e.getKey(), e.getValue().isEmpty() ? 0 : (c * 100.0) / e.getValue().size());
        }
List<MockInterviewSession> sessions =
        mockInterviewSessionRepository.findByUserIdOrderByCreatedAtDesc(userId);

int mockCompleted = (int) sessions.stream()
        .filter(s -> "COMPLETED".equals(s.getStatus()))
        .count();

Integer latestScore = null;
String latestFeedback = null;

for (MockInterviewSession session : sessions) {

    if ("COMPLETED".equals(session.getStatus())) {

        latestScore = session.getFinalScore();
        latestFeedback = session.getFinalFeedback();

        break;
    }
}

int resumeRuns =
        resumeAnalysisRepository.findByUserIdOrderByCreatedAtDesc(userId).size();

return new ProgressResponse(

        total,
        correct,
        Math.round(accuracy * 10) / 10.0,

        attemptedByCategory,
        correctByCategory,
        accuracyByDifficulty,

        mockCompleted,
        resumeRuns,

        latestScore,
        latestFeedback
);
       
    }
}
