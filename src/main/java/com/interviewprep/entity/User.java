package com.interviewprep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    // Onboarding / personalization fields
    private String targetJobRole;      // e.g. "Backend Developer", "Full Stack Developer"
    private String targetCompanyType;  // e.g. "Product", "Service", "Startup"
    private Double targetLpa;          // e.g. 12.5
    private String experienceLevel;    // "Fresher", "1-3 yrs", "3-5 yrs", "5+ yrs"

    @Column(columnDefinition = "TEXT")
    private String resumeText; // last uploaded resume, plain text extracted

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
