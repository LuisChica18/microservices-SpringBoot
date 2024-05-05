package com.example.microservicesanswers.repository;

import com.example.microservicesanswers.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a JOIN FETCH a.question q JOIN FETCH q.assestment asse WHERE a.studentId=?1 and asse.id=?2")
    public Iterable<Answer> findAnswersByStudentByAssestment(Long studentId, Long assestmentId);

    @Query("SELECT asse.id from Answer a JOIN a.question q JOIN q.assestment asse WHERE a.studentId=?1 GROUP BY asse.id")
    public Iterable<Long> findAssestmentIdsWithAnswersByStudents(Long studentId);
}
