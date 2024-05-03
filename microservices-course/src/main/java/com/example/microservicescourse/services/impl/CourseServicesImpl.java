package com.example.microservicescourse.services.impl;

import com.example.commonsdomain.entity.Student;
import com.example.commonsmicroservices.service.impl.CommonServicesImpl;
import com.example.microservicescourse.clients.AnswerClientFeign;
import com.example.microservicescourse.clients.StudentClientFeign;
import com.example.microservicescourse.entity.Course;
import com.example.microservicescourse.repository.CourseRepository;
import com.example.microservicescourse.services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServicesImpl extends CommonServicesImpl<Course, CourseRepository> implements CourseServices {

    @Autowired
    AnswerClientFeign client;

    @Autowired
    StudentClientFeign studentClientFeign;

    @Override
    @Transactional(readOnly = true)
    public Course findCourseByStudentId(Long id) {
        return repository.findCourseByStudentId(id);
    }

    @Override
    public Iterable<Long> findAssestmentIdsWithAnswersByStudents(Long studentId) {
        return client.findAssestmentIdsWithAnswersByStudents(studentId);
    }

    @Override
    public Iterable<Student> getStudentsById(List<Long> ids) {
        return studentClientFeign.getStudentsById(ids);
    }
}
