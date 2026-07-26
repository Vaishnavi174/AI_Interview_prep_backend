package com.interviewprep.repository;

import com.interviewprep.entity.QuestionCategory;
import com.interviewprep.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByUserId(Long userId);
    List<UserAnswer> findByUserIdAndQuestionCategory(Long userId, QuestionCategory category);
}
