package com.example.microservicesanswers.controller;

import com.example.microservicesanswers.entity.Answer;
import com.example.microservicesanswers.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Iterable<Answer> answers){
        answers = ((List<Answer>) answers).stream()
                .peek(answer -> answer.setStudentId(answer.getStudent().getId()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(answerService.saveAll(answers));
    }

    @GetMapping("/student/{studentId}/assestment/{assestmentId}")
    public ResponseEntity<?> getAnswerByStudentByAssestment(@PathVariable Long studentId, @PathVariable Long assestmentId){
        return ResponseEntity.ok(answerService.findAnswersByStudentByAssestment(studentId, assestmentId));
    }

    @GetMapping("/student/{studentId}/assestment-response")
    public ResponseEntity<?> findAssestmentIdsWithAnswersByStudents(@PathVariable Long studentId){
        return ResponseEntity.ok(answerService.findAssestmentIdsWithAnswersByStudents(studentId));
    }
}
