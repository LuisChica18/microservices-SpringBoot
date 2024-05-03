package com.example.microservicescourse.entity;

import com.example.commonsdomain.entity.Student;
import com.example.commonsdomainassestment.entity.Assestment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @JsonIgnoreProperties(value = {"course"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseStudent> courseStudents;

    //@OneToMany(fetch = FetchType.LAZY)
    @Transient
    private List<Student> studentList;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Assestment> assestments;

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public Course() {
        this.studentList = new ArrayList<>();
        this.assestments = new ArrayList<>();
        this.courseStudents = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudent(Student student) {
        this.studentList.add(student);
    }

    public void removeStudent(Student student) {
        this.studentList.remove(student);
    }

    public List<Assestment> getAssestments() {
        return assestments;
    }

    public void setAssestments(List<Assestment> assestments) {
        this.assestments = assestments;
    }

    public void addAssestments(Assestment assestment) {
        this.assestments.add(assestment);
    }

    public void removeAssestments(Assestment assestment) {
        this.assestments.remove(assestment);
    }

    public List<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public void addCourseStudents(CourseStudent courseStudent) {
        this.courseStudents.add(courseStudent);
    }

    public void removeCourseStudents(CourseStudent courseStudent) {
        this.courseStudents.remove(courseStudent);
    }
}
