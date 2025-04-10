package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Question;
import com.example.backend.service.QuizService;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/questions")
    public List<Question> fetchQuestions() {
        return quizService.retrieveQuestions();
    }

    @PostMapping("/submit")
    public Map<String, Integer> evaluateAnswers(@RequestBody Map<Integer, String> userAnswers) {
        int totalScore = quizService.computeScore(userAnswers);
        return Map.of("score", totalScore);
    }
}