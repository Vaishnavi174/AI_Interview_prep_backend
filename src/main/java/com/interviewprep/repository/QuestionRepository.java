package com.interviewprep.repository;

import com.interviewprep.entity.Difficulty;
import com.interviewprep.entity.Question;
import com.interviewprep.entity.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(QuestionCategory category);
    List<Question> findByCategoryAndDifficulty(QuestionCategory category, Difficulty difficulty);
    List<Question> findByCategoryAndTopic(QuestionCategory category, String topic);
    List<Question> findByCategoryAndDifficultyAndTopic(QuestionCategory category, Difficulty difficulty, String topic);
}
