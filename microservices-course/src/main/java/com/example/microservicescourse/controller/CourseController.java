package com.example.microservicescourse.controller;

import com.example.commonsdomain.entity.Student;
import com.example.commonsdomainassestment.entity.Assestment;
import com.example.commonsmicroservices.controller.CommonController;
import com.example.microservicescourse.entity.Course;
import com.example.microservicescourse.entity.CourseStudent;
import com.example.microservicescourse.services.CourseServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseController extends CommonController<Course, CourseServices> {

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Course course, @PathVariable Long id){
        Optional<Course> obj = services.findById(id);
        if (!obj.isPresent())
            return ResponseEntity.notFound().build();

        Course courseDb = obj.get();
        courseDb.setName(course.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(courseDb));
    }

    @GetMapping("/")
    @Override
    public ResponseEntity<?> list(){
        List<Course> courses = ((List<Course>) services.findAll()).stream().map(course -> {
            course.getCourseStudents().forEach(courseStudent -> {
                Student student = new Student();
                student.setId(courseStudent.getStudentId());
                course.addStudent(student);
            });
            return course;
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/page")
    @Override
    public ResponseEntity<?> list(Pageable pageable){
        Page<Course> courses = services.findAll(pageable).map(course -> {
            course.getCourseStudents().forEach(courseStudent -> {
                Student student = new Student();
                student.setId(courseStudent.getStudentId());
                course.addStudent(student);
            });
            return course;
        });
        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Course> obj = services.findById(id);
        if (obj.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Course course = obj.get();
        List<Long> ids = null;
        if (!course.getCourseStudents().isEmpty()) {
            ids = course.getCourseStudents().stream().map(CourseStudent::getStudentId).collect(Collectors.toList());
            List<Student> studentList = (List<Student>) services.getStudentsById(ids);
            course.setStudentList(studentList);
        }
        return ResponseEntity.ok().body(course);
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<?> deleteCourseStudentbyId(@PathVariable Long id){
        services.deleteCourseStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/add-students")
    public ResponseEntity<?> addStudentstoCourse(@RequestBody List<Student> students, @PathVariable Long id){
        Optional<Course> obj = services.findById(id);
        if (!obj.isPresent())
            return ResponseEntity.notFound().build();

        Course courseDb = obj.get();
        //students.forEach(courseDb::addStudent);
        students.forEach(student -> {
            CourseStudent courseStudent = new CourseStudent();
            courseStudent.setStudentId(student.getId());
            courseStudent.setCourse(courseDb);
            courseDb.addCourseStudents(courseStudent);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(courseDb));
    }

    @PutMapping("/{id}/remove-student")
    public ResponseEntity<?> removeStudenttoCourse(@RequestBody Student student, @PathVariable Long id){
        Optional<Course> obj = services.findById(id);
        if (!obj.isPresent())
            return ResponseEntity.notFound().build();

        Course courseDb = obj.get();
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudentId(student.getId());
        courseDb.removeCourseStudents(courseStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(courseDb));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findCourseByStudentId(@PathVariable Long id){
        Course course = services.findCourseByStudentId(id);

        if (!Objects.isNull(course)) {
            List<Long> assestmentsId = (List<Long>) services.findAssestmentIdsWithAnswersByStudents(id);
            List<Assestment> assestments = course.getAssestments().stream().map(assestment -> {
                if (assestmentsId.contains(assestment.getId())){
                    assestment.setAnswered(true);
                }
                return assestment;
            }).collect(Collectors.toList());

            course.setAssestments(assestments);
        }
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}/add-assestment")
    public ResponseEntity<?> addAssestmenttoCourse(@RequestBody List<Assestment> assestments, @PathVariable Long id){
        Optional<Course> obj = services.findById(id);
        if (!obj.isPresent())
            return ResponseEntity.notFound().build();

        Course courseDb = obj.get();
        assestments.forEach(courseDb::addAssestments);
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(courseDb));
    }

    @PutMapping("/{id}/remove-assestment")
    public ResponseEntity<?> removeAssestmenttoCourse(@RequestBody Assestment assestment, @PathVariable Long id){
        Optional<Course> obj = services.findById(id);
        if (!obj.isPresent())
            return ResponseEntity.notFound().build();

        Course courseDb = obj.get();
        courseDb.removeAssestments(assestment);
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(courseDb));
    }
}
