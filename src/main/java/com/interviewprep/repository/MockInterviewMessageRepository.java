package com.interviewprep.repository;

import com.interviewprep.entity.MockInterviewMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MockInterviewMessageRepository extends JpaRepository<MockInterviewMessage, Long> {
    List<MockInterviewMessage> findBySessionIdOrderBySentAtAsc(Long sessionId);
}
