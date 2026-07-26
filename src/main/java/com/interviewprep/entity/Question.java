package com.interviewprep.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Generic question entity shared by Java, DSA and Aptitude modules.
 * "topic" holds the sub-category, e.g. for APTITUDE: "Quantitative", "Logical Reasoning", "Verbal";
 * for DSA: "Arrays", "Trees", "Graphs", "Dynamic Programming"; for JAVA: "OOP", "Collections", "Multithreading" etc.
 */
@Entity
@Table(name = "questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private QuestionCategory category;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String topic;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionText;

    // For MCQ style questions (aptitude, java theory). Nullable for open-ended DSA problems.
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption; // "A" / "B" / "C" / "D"

    @Column(columnDefinition = "TEXT")
    private String explanation;

    // Applicable job roles this question is relevant for, comma-separated (empty = relevant to all)
    private String relevantJobRoles;
}
