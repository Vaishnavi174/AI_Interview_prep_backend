package com.interviewprep.repository;

import com.interviewprep.entity.MockInterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MockInterviewSessionRepository extends JpaRepository<MockInterviewSession, Long> {

    List<MockInterviewSession> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Eagerly fetches messages in the same query so the collection is initialized
    // before the transaction closes (avoids LazyInitializationException on serialization).
    @Query("SELECT DISTINCT s FROM MockInterviewSession s LEFT JOIN FETCH s.messages " +
           "WHERE s.user.id = :userId ORDER BY s.createdAt DESC")
    List<MockInterviewSession> findByUserIdWithMessages(@Param("userId") Long userId);
}

