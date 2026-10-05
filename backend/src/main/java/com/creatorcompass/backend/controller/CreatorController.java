package com.creatorcompass.backend.controller;

import com.creatorcompass.backend.dto.CreatorRequest;
import com.creatorcompass.backend.service.OllamaService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/creator")
@CrossOrigin(origins = "http://localhost:5173")
public class CreatorController {

    private final OllamaService ollamaService;

    public CreatorController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/advice")
    public Map<String, String> getAdvice(
            @RequestBody CreatorRequest request) {

        String advice = ollamaService.getAdvice(request);

        return Map.of(
                "advice", advice
        );
    }
}