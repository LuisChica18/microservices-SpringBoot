package com.example.microservicescourse.clients;

import com.example.commonsdomain.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="microservices-student")
public interface StudentClientFeign {

    @GetMapping("/studentsList")
    public Iterable<Student> getStudentsById(@RequestParam List<Long> ids);
}
