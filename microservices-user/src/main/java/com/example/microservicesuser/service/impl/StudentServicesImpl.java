package com.example.microservicesuser.service.impl;

import com.example.commonsdomain.entity.Student;
import com.example.commonsmicroservices.service.impl.CommonServicesImpl;
import com.example.microservicesuser.repository.StudentRepository;
import com.example.microservicesuser.service.StudentServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServicesImpl extends CommonServicesImpl<Student, StudentRepository> implements StudentServices {

    @Override
    @Transactional(readOnly = true)
    public List<Student> findByNameOrLastname(String term) {
        return repository.findByNameOrLastname(term);
    }

    @Override
    public Iterable<Student> findStudentsById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }
}
