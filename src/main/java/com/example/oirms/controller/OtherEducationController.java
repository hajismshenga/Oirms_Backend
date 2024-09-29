package com.example.oirms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.oirms.model.OtherEducation;
import com.example.oirms.service.OtherEducationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/other-education")
public class OtherEducationController {

    @Autowired
    private OtherEducationService service;

    private static final String UPLOAD_DIR = "uploads/"; // Directory for file uploads

    @GetMapping("/all")
    public ResponseEntity<List<OtherEducation>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<OtherEducation> save(
            @RequestParam("typeOfEducation") String typeOfEducation,
            @RequestParam("institutionName") String institutionName,
            @RequestParam("registrationNumber") String registrationNumber,
            @RequestParam("startYear") Integer startYear,
            @RequestParam("endYear") Integer endYear,
            @RequestParam("certificate") MultipartFile certificate
    ) throws IOException {
        OtherEducation otherEducation = new OtherEducation();
        otherEducation.setTypeOfEducation(typeOfEducation);
        otherEducation.setInstitutionName(institutionName);
        otherEducation.setRegistrationNumber(registrationNumber);
        otherEducation.setStartYear(startYear);
        otherEducation.setEndYear(endYear);

        // Save file to the server
        if (!certificate.isEmpty()) {
            String fileName = certificate.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, certificate.getBytes());
            otherEducation.setCertificatePath(filePath.toString());
        }

        return new ResponseEntity<>(service.save(otherEducation), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OtherEducation> update(
            @PathVariable Long id,
            @RequestParam("typeOfEducation") String typeOfEducation,
            @RequestParam("institutionName") String institutionName,
            @RequestParam("registrationNumber") String registrationNumber,
            @RequestParam("startYear") Integer startYear,
            @RequestParam("endYear") Integer endYear,
            @RequestParam(value = "certificate", required = false) MultipartFile certificate) throws IOException {
        
        OtherEducation updatedEducation = service.update(id, typeOfEducation, institutionName, registrationNumber, startYear, endYear, certificate);
        return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
