package com.example.microservicescourse.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservices-answers")
public interface AnswerClientFeign {

    @GetMapping("/student/{studentId}/assestment-response")
    public Iterable<Long> findAssestmentIdsWithAnswersByStudents(@PathVariable Long studentId);

}
