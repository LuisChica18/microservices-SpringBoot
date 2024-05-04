package com.example.microservicesuser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservices-course")
public interface CourseFeingClient {

    @DeleteMapping("/delete-student/{id}")
    public void deleteCourseStudentbyId(@PathVariable Long id);
}
