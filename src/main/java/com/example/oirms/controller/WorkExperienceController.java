package com.example.oirms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.oirms.model.WorkExperience;
import com.example.oirms.service.WorkExperienceService;

import java.util.List;

@RestController
@RequestMapping("/work-experience")
public class WorkExperienceController {

    @Autowired
    private WorkExperienceService service;

    @GetMapping("/all")
    public ResponseEntity<List<WorkExperience>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<WorkExperience> save(@RequestBody WorkExperience workExperience) {
        return new ResponseEntity<>(service.save(workExperience), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WorkExperience> update(@PathVariable Long id, @RequestBody WorkExperience workExperience) {
        return new ResponseEntity<>(service.update(id, workExperience), HttpStatus.OK);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
