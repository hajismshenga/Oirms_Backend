package com.example.oirms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.oirms.model.PrimaryEducation;
import com.example.oirms.service.PrimaryEducationService;

import java.util.List;

@RestController
@RequestMapping("/primary-education")
public class PrimaryEducationController {

    @Autowired
    private PrimaryEducationService service;

    @GetMapping("/all")
    public ResponseEntity<List<PrimaryEducation>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<PrimaryEducation> save(@RequestBody PrimaryEducation primaryEducation) {
        return new ResponseEntity<>(service.save(primaryEducation), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
public ResponseEntity<PrimaryEducation> update(@PathVariable Long id, @RequestBody PrimaryEducation primaryEducation) {
    return new ResponseEntity<>(service.update(id, primaryEducation), HttpStatus.OK);
}



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
