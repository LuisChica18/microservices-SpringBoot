package com.example.commonsmicroservices.controller;

import com.example.commonsmicroservices.service.CommonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonController<E, S extends CommonServices<E>> {

    @Autowired
    protected S services;

    @GetMapping("/")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(services.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<?> list(Pageable pageable){
        return ResponseEntity.ok().body(services.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<E> obj = services.findById(id);

        if (obj.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(obj.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody E student, BindingResult result){

        // call errors validate
        if (result.hasErrors())
            return this.validate(result);

        E entityDb = services.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<E> obj = services.findById(id);
        if (obj.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        services.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Method for validation errors
    protected ResponseEntity<?> validate(BindingResult result){
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), " The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
