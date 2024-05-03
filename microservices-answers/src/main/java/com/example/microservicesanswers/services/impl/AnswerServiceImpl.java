package com.example.microservicesanswers.services.impl;

import com.example.microservicesanswers.entity.Answer;
import com.example.microservicesanswers.repository.AnswerRepository;
import com.example.microservicesanswers.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Override
    @Transactional
    public Iterable<Answer> saveAll(Iterable<Answer> answers) {
        return answerRepository.saveAll(answers);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Answer> findAnswersByStudentByAssestment(Long studentId, Long assestmentId) {
        return answerRepository.findAnswersByStudentByAssestment(studentId, assestmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Long> findAssestmentIdsWithAnswersByStudents(Long studentId) {
        return answerRepository.findAssestmentIdsWithAnswersByStudents(studentId);
    }
}
