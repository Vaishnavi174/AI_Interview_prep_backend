package com.interviewprep.controller;

import com.interviewprep.dto.OnboardingRequest;
import com.interviewprep.entity.User;
import com.interviewprep.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(userService.getById(userId));
    }

    @PostMapping("/onboarding")
    public ResponseEntity<User> completeOnboarding(HttpServletRequest request, @Valid @RequestBody OnboardingRequest req) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(userService.completeOnboarding(userId, req));
    }
}
