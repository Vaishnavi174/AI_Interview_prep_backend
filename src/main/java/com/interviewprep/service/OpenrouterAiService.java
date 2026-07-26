package com.interviewprep.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OpenrouterAiService {

@Value("${spring.ai.openrouter.api-key}")
private String apiKey;

@Value("${spring.ai.openrouter.chat.options.model}")
private String model;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public String complete(String systemPrompt, String userPrompt) {
        return completeConversation(
                systemPrompt,
                List.of(Map.of(
                        "role", "user",
                        "content", userPrompt
                ))
        );
    }

    public String completeConversation(String systemPrompt,
                                       List<Map<String, String>> history) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        headers.set("HTTP-Referer", "http://localhost:5173");
        headers.set("X-Title", "Interview Prep Platform");

        // Include system prompt as the first message
        List<Map<String, String>> messages = new java.util.ArrayList<>();
        messages.add(Map.of(
                "role", "system",
                "content", systemPrompt
        ));
        messages.addAll(history);

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", messages
        );

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        try {

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://openrouter.ai/api/v1/chat/completions",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            JsonNode root = mapper.readTree(response.getBody());

            return root.path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI Error: " + e.getMessage();
        }
    }
}