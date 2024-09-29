package com.example.oirms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.oirms.model.DemographicInfo;
import com.example.oirms.service.DemographicInfoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/demographic")
public class DemographicInfoController {

    @Autowired
    private DemographicInfoService service;

    private static final String UPLOAD_DIR = "uploads/"; // Directory for file uploads

    @GetMapping("/all")
    public ResponseEntity<List<DemographicInfo>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    // Save demographic info with file upload
    @PostMapping("/save")
    public ResponseEntity<DemographicInfo> save(
            @RequestParam("name") String name,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("address") String address,
            @RequestParam("nationality") String nationality,
            @RequestParam("gender") String gender,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("birthCertificate") MultipartFile birthCertificate
    ) throws IOException {

        DemographicInfo demographicInfo = new DemographicInfo();
        demographicInfo.setName(name);
        demographicInfo.setDateOfBirth(java.sql.Date.valueOf(dateOfBirth));
        demographicInfo.setAddress(address);
        demographicInfo.setNationality(nationality);
        demographicInfo.setGender(gender);
        demographicInfo.setPhoneNumber(phoneNumber);
        demographicInfo.setEmail(email);

        // Save file to the server
        if (!birthCertificate.isEmpty()) {
            String fileName = birthCertificate.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);

            // Create directories if they don't exist
            Files.createDirectories(filePath.getParent());

            // Save the file to the uploads folder
            Files.write(filePath, birthCertificate.getBytes());

            // Set the file path in the demographicInfo model
            demographicInfo.setBirthCertificatePath(filePath.toString()); // Storing the file path
        }

        return new ResponseEntity<>(service.save(demographicInfo), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}


    // Download the birth certificate
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadBirthCertificate(@PathVariable Long id) throws IOException {
        DemographicInfo info = service.getById(id);
        Path path = Paths.get(info.getBirthCertificatePath());
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .body(fileBytes);
    }
}
