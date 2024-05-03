package com.example.microservicesanswers.services;

import com.example.microservicesanswers.entity.Answer;

public interface AnswerService {

    public Iterable<Answer> saveAll(Iterable<Answer> answers);

    public Iterable<Answer> findAnswersByStudentByAssestment(Long studentId, Long assestmentId);

    public Iterable<Long> findAssestmentIdsWithAnswersByStudents(Long studentId);
}
