package com.example.oirms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.oirms.model.AdvancedEducation;
import com.example.oirms.service.AdvancedEducationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/advanced-education")
public class AdvancedEducationController {

    @Autowired
    private AdvancedEducationService service;

    private static final String UPLOAD_DIR = "uploads/"; // Directory for file uploads

    @GetMapping("/all")
    public ResponseEntity<List<AdvancedEducation>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AdvancedEducation> save(
            @RequestParam("institutionName") String institutionName,
            @RequestParam("registrationNumber") String registrationNumber,
            @RequestParam("courseType") String courseType,
            @RequestParam("courseName") String courseName,
            @RequestParam("startYear") Integer startYear,
            @RequestParam("endYear") Integer endYear,
            @RequestParam("certificate") MultipartFile certificate
    ) throws IOException {
        AdvancedEducation advancedEducation = new AdvancedEducation();
        advancedEducation.setInstitutionName(institutionName);
        advancedEducation.setRegistrationNumber(registrationNumber);
        advancedEducation.setCourseType(courseType);
        advancedEducation.setCourseName(courseName);
        advancedEducation.setStartYear(startYear);
        advancedEducation.setEndYear(endYear);

        // Save file to the server
        if (!certificate.isEmpty()) {
            String fileName = certificate.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, certificate.getBytes());
            advancedEducation.setCertificatePath(filePath.toString());
        }

        return new ResponseEntity<>(service.save(advancedEducation), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdvancedEducation> update(
            @PathVariable Long id,
            @RequestParam("institutionName") String institutionName,
            @RequestParam("registrationNumber") String registrationNumber,
            @RequestParam("courseType") String courseType,
            @RequestParam("courseName") String courseName,
            @RequestParam("startYear") Integer startYear,
            @RequestParam("endYear") Integer endYear,
            @RequestParam(value = "certificate", required = false) MultipartFile certificate) throws IOException {
        
        AdvancedEducation updatedEducation = service.update(id, institutionName, registrationNumber, courseType, courseName, startYear, endYear, certificate);
        return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
