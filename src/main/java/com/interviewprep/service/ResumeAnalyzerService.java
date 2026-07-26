package com.interviewprep.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewprep.dto.ResumeAnalyzeRequest;
import com.interviewprep.entity.ResumeAnalysis;
import com.interviewprep.entity.User;
import com.interviewprep.repository.ResumeAnalysisRepository;
import com.interviewprep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeAnalyzerService {

    private final OpenrouterAiService openrouterAiService;
    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

   private static final String SYSTEM_PROMPT = """
            You are an expert technical recruiter and resume coach. You will be given a candidate's resume text
            and their target job role. Analyze the resume STRICTLY from the lens of that target role.
            Respond with ONLY a raw JSON object (no markdown fences, no preamble, no restating these instructions,
            no repeating the resume text back) with exactly these fields:
            {
              "overallScore": <integer 0-100>,
              "strengths": "<2-4 sentences>",
              "weaknesses": "<2-4 sentences>",
              "suggestions": "<3-5 concrete, actionable bullet points separated by newlines>",
              "missingKeywords": "<comma separated list of important keywords/skills missing for the target role>"
            }
            """;

    public ResumeAnalysis analyze(Long userId, ResumeAnalyzeRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String jobRole = req.getTargetJobRole() != null && !req.getTargetJobRole().isBlank()
                ? req.getTargetJobRole()
                : user.getTargetJobRole();
        if (jobRole == null) jobRole = "Software Engineer";

        // persist resume text on the profile for reuse (e.g. mock interview personalization)
        user.setResumeText(req.getResumeText());
        userRepository.save(user);

        String userMessage = "Target job role: " + jobRole + "\n\nResume:\n" + req.getResumeText();
        String aiResponse = openrouterAiService.complete(SYSTEM_PROMPT, userMessage);

        ResumeAnalysis.ResumeAnalysisBuilder builder = ResumeAnalysis.builder()
                .user(user)
                .targetJobRole(jobRole);

        try {
            String cleaned = aiResponse.trim().replaceAll("^```json", "").replaceAll("^```", "").replaceAll("```$", "");
            JsonNode node = objectMapper.readTree(cleaned);
            builder.overallScore(node.path("overallScore").asInt(0))
                   .strengths(node.path("strengths").asText(""))
                   .weaknesses(node.path("weaknesses").asText(""))
                   .suggestions(node.path("suggestions").asText(""))
                   .missingKeywords(node.path("missingKeywords").asText(""));
        } catch (Exception e) {
            // AI didn't return clean JSON (or fallback/no-key message) - surface raw text so the user still sees something
            builder.overallScore(0)
                   .strengths("")
                   .weaknesses("")
                   .suggestions(aiResponse)
                   .missingKeywords("");
        }

        return resumeAnalysisRepository.save(builder.build());
    }
}
