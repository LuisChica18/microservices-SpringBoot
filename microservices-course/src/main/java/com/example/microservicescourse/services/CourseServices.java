package com.example.microservicescourse.services;

import com.example.commonsdomain.entity.Student;
import com.example.commonsmicroservices.service.CommonServices;
import com.example.microservicescourse.entity.Course;

import java.util.List;

public interface CourseServices extends CommonServices<Course> {

    public Course findCourseByStudentId(Long id);

    public Iterable<Long> findAssestmentIdsWithAnswersByStudents(Long studentId);

    public Iterable<Student> getStudentsById(List<Long> ids);
}
