package com.interviewprep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mock_interview_messages")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MockInterviewMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private MockInterviewSession session;

    private String role; // "INTERVIEWER" or "CANDIDATE"

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder.Default
    private LocalDateTime sentAt = LocalDateTime.now();
}
