package com.interviewprep.service;

import com.interviewprep.dto.OnboardingRequest;
import com.interviewprep.entity.User;
import com.interviewprep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User completeOnboarding(Long userId, OnboardingRequest req) {
        User user = getById(userId);
        user.setTargetJobRole(req.getTargetJobRole());
        user.setTargetCompanyType(req.getTargetCompanyType());
        user.setTargetLpa(req.getTargetLpa());
        user.setExperienceLevel(req.getExperienceLevel());
        return userRepository.save(user);
    }
}
