package com.example.microservicesuser.service;

import com.example.commonsdomain.entity.Student;
import com.example.commonsmicroservices.service.CommonServices;

import java.util.List;

public interface StudentServices extends CommonServices<Student> {

    public List<Student> findByNameOrLastname(String term);

    public Iterable<Student> findStudentsById(Iterable<Long> ids);
}
