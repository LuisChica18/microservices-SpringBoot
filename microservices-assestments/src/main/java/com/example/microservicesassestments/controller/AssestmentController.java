package com.example.microservicesassestments.controller;

import com.example.commonsdomainassestment.entity.Assestment;
import com.example.commonsmicroservices.controller.CommonController;
import com.example.microservicesassestments.services.AssestmentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AssestmentController extends CommonController<Assestment, AssestmentServices> {

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Assestment assestment, BindingResult result, @PathVariable Long id){

        // call errors validate
        if (result.hasErrors())
            return this.validate(result);

        Optional<Assestment> obj = services.findById(id);
        if (!obj.isPresent())
            return ResponseEntity.notFound().build();

        Assestment assestmentDb = obj.get();
        assestmentDb.setName(assestment.getName());

        assestmentDb.getQuestions().stream()
                .filter(question -> !assestment.getQuestions().contains(question))
                .forEach(assestmentDb::removeQuestion);

        assestmentDb.setQuestions(assestment.getQuestions());
        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(assestmentDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> findByName(@PathVariable String term){
        return ResponseEntity.ok(services.findByNombre(term));
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> listSubjects(){
        return ResponseEntity.ok(services.findAllSubjects());
    }
}
