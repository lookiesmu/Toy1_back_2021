package com.lookie.toy1_back.tome.controller;

import com.lookie.toy1_back.tome.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionRepository questionRepository;

    @GetMapping
    public ResponseEntity<?> getQuestion(){
        return ResponseEntity.ok(questionRepository.findAll());
    }
}