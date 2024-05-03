package com.example.microservicesuser.controller;

import com.example.commonsmicroservices.controller.CommonController;
import com.example.commonsdomain.entity.Student;
import com.example.microservicesuser.service.StudentServices;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController extends CommonController<Student, StudentServices> {

    @GetMapping("/studentsList")
    public ResponseEntity<?> getStudentsById(@RequestParam List<Long> ids){
        return ResponseEntity.ok(services.findStudentsById(ids));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@Valid @RequestBody Student student, BindingResult result, @PathVariable Long id){

        // call errors validate
        if (result.hasErrors())
            return this.validate(result);

        Optional<Student> obj = services.findById(id);

        if (obj.isEmpty()){
            ResponseEntity.notFound().build();
        }

        Student studentDb = obj.get();
        studentDb.setName(student.getName());
        studentDb.setLastname(student.getLastname());
        studentDb.setEmail(student.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(studentDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar (@PathVariable String term){
        return ResponseEntity.ok(services.findByNameOrLastname(term));
    }

    @GetMapping("/view-photo/{id}")
    public ResponseEntity<?> viewPhoto (@PathVariable Long id){
        Optional<Student> obj = services.findById(id);
        if (obj.isEmpty() || obj.get().getPhoto() == null)
            return ResponseEntity.notFound().build();

        Resource photo = new ByteArrayResource(obj.get().getPhoto());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(photo);
    }

    @PostMapping("/save-with-photo")
    public ResponseEntity<?> saveWithPhoto(@Valid Student student, BindingResult result, @RequestParam MultipartFile file) throws IOException {
        if (!file.isEmpty())
            student.setPhoto(file.getBytes());
        return super.save(student, result);
    }

    @PutMapping("update-with-photo/{id}")
    public ResponseEntity<?> modifyWithPhoto(@Valid Student student, BindingResult result, @PathVariable Long id,
                                             @RequestParam MultipartFile file) throws IOException {

        // call errors validate
        if (result.hasErrors())
            return this.validate(result);

        Optional<Student> obj = services.findById(id);

        if (obj.isEmpty()){
            ResponseEntity.notFound().build();
        }

        Student studentDb = obj.get();
        studentDb.setName(student.getName());
        studentDb.setLastname(student.getLastname());
        studentDb.setEmail(student.getEmail());
        if (!file.isEmpty())
            studentDb.setPhoto(file.getBytes());

        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(studentDb));
    }
}
