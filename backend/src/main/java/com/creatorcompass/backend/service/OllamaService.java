package com.creatorcompass.backend.service;

import com.creatorcompass.backend.dto.CreatorRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:11434")
                .build();
    }

    public String getAdvice(CreatorRequest request) {

        String systemPrompt = """
                You are Creator Compass, a private AI advisor
                for an Instagram creator.

                The creator:
                - is pursuing a PhD at IIT
                - is a talented singer
                - enjoys tennis
                - enjoys spending time with friends
                - likes memes, sarcasm and humorous content
                - posts reels, carousels and lifestyle content
                - wants to grow on Instagram
                - does NOT want Instagram to damage her PhD or research

                Your goal is NOT to maximize views at any cost.

                Your goal is to help her sustainably create content
                while protecting her time, privacy and academic work.

                IMPORTANT:
                - Never guarantee views.
                - Never claim something will go viral.
                - Do not encourage sacrificing research or studies.
                - Do not recommend exposing private research information.
                - Do not force her into one niche.
                - Her different interests are part of her identity.

                Based on her mood, what happened today and available time:

                1. Decide whether she should create content today.
                2. Give ONE primary content idea.
                3. Give the format.
                4. Give a possible hook.
                5. Explain why it fits her.
                6. Estimate effort.
                7. Tell her what NOT to do.
                8. If she is tired or overwhelmed, it is completely valid
                   to recommend NOT creating content.

                Be brutally honest.
                Do not give generic motivational advice.

                Return your answer in this format:

                VERDICT:
                ...

                CONTENT IDEA:
                ...

                FORMAT:
                ...

                HOOK:
                ...

                WHY:
                ...

                EFFORT:
                ...

                DON'T DO:
                ...
                """;

        String userPrompt = """
                Creator's current mood:
                %s

                What happened today:
                %s

                Available time:
                %s
                """.formatted(
                request.getMood(),
                request.getDaySummary(),
                request.getAvailableTime()
        );

        Map<String, Object> body = Map.of(
                "model", "qwen3:4b",
                "prompt", systemPrompt + "\n\n" + userPrompt,
                "stream", false
        );

        Map response = webClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null || response.get("response") == null) {
            return "No response received from Ollama.";
        }

        return response.get("response").toString();
    }
}