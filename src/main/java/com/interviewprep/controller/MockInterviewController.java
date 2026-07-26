package com.interviewprep.controller;

import com.interviewprep.dto.MockInterviewReplyRequest;
import com.interviewprep.dto.MockInterviewStartRequest;
import com.interviewprep.entity.MockInterviewMessage;
import com.interviewprep.entity.MockInterviewSession;
import com.interviewprep.service.MockInterviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mock-interview")
@RequiredArgsConstructor
public class MockInterviewController {

    private final MockInterviewService mockInterviewService;

    @PostMapping("/start")
    public ResponseEntity<MockInterviewSession> start(HttpServletRequest request, @RequestBody MockInterviewStartRequest req) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(mockInterviewService.startSession(userId, req));
    }

    @PostMapping("/{sessionId}/reply")
    public ResponseEntity<MockInterviewMessage> reply(HttpServletRequest request, @PathVariable Long sessionId,
                                                        @Valid @RequestBody MockInterviewReplyRequest req) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(mockInterviewService.reply(userId, sessionId, req.getMessage()));
    }
    @PostMapping("/{sessionId}/finish")
public ResponseEntity<String> finishInterview(@PathVariable Long sessionId) {

    return ResponseEntity.ok(
            mockInterviewService.finishInterview(sessionId)
    );
}

    @GetMapping("/{sessionId}/messages")
    public ResponseEntity<List<MockInterviewMessage>> messages(@PathVariable Long sessionId) {
        return ResponseEntity.ok(mockInterviewService.getMessages(sessionId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<MockInterviewSession>> history(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(mockInterviewService.getUserSessions(userId));
    }
}
