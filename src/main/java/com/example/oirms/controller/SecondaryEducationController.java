package com.example.oirms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.oirms.model.SecondaryEducation;
import com.example.oirms.service.SecondaryEducationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/secondary-education")
public class SecondaryEducationController {

    @Autowired
    private SecondaryEducationService service;

    private static final String UPLOAD_DIR = "uploads/"; // Directory for file uploads

    @GetMapping("/all")
    public ResponseEntity<List<SecondaryEducation>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<SecondaryEducation> save(
            @RequestParam("school") String school,
            @RequestParam("district") String district,
            @RequestParam("region") String region,
            @RequestParam("startYear") Integer startYear,
            @RequestParam("endYear") Integer endYear,
            @RequestParam("indexNumber") String indexNumber,
            @RequestParam("certificate") MultipartFile certificate
    ) throws IOException {
        SecondaryEducation secondaryEducation = new SecondaryEducation();
        secondaryEducation.setSchool(school);
        secondaryEducation.setDistrict(district);
        secondaryEducation.setRegion(region);
        secondaryEducation.setStartYear(startYear);
        secondaryEducation.setEndYear(endYear);
        secondaryEducation.setIndexNumber(indexNumber);

        // Save file to the server
        if (!certificate.isEmpty()) {
            String fileName = certificate.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, certificate.getBytes());
            secondaryEducation.setCertificatePath(filePath.toString());
        }

        return new ResponseEntity<>(service.save(secondaryEducation), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SecondaryEducation> update(
            @PathVariable Long id,
            @RequestParam("school") String school,
            @RequestParam("district") String district,
            @RequestParam("region") String region,
            @RequestParam("startYear") Integer startYear,
            @RequestParam("endYear") Integer endYear,
            @RequestParam("indexNumber") String indexNumber,
            @RequestParam(value = "certificate", required = false) MultipartFile certificate) throws IOException {
        
        SecondaryEducation updatedEducation = service.update(id, school, district, region, startYear, endYear, indexNumber, certificate);
        return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
