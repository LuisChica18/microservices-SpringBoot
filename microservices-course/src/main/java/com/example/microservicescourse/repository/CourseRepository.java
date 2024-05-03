package com.example.microservicescourse.repository;

import com.example.microservicescourse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c JOIN FETCH c.courseStudents s WHERE s.studentId=?1")
    public Course findCourseByStudentId(Long id);
}
