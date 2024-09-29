package com.example.oirms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.oirms.model.Profile;
import com.example.oirms.service.ProfileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService service;

    private static final String UPLOAD_DIR = "uploads/"; // Directory for file uploads

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        return service.getProfile(id)
                .map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Profile> save(
            @RequestParam("lastName") String lastName,
            @RequestParam("firstName") String firstName,
            @RequestParam("secondName") String secondName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("employmentNumber") String employmentNumber,
            @RequestParam("employmentStatus") String employmentStatus,
            @RequestParam("department") String department,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) throws IOException {

        Profile profile = new Profile();
        profile.setLastName(lastName);
        profile.setFirstName(firstName);
        profile.setSecondName(secondName);
        profile.setEmail(email);
        profile.setPhoneNumber(phoneNumber);
        profile.setEmploymentNumber(employmentNumber);
        profile.setEmploymentStatus(employmentStatus);
        profile.setDepartment(department);

        // Save file to the server
        if (profilePicture != null && !profilePicture.isEmpty()) {
            String fileName = profilePicture.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, profilePicture.getBytes());
            profile.setProfilePicturePath(filePath.toString());
        }

        return new ResponseEntity<>(service.save(profile), HttpStatus.CREATED);
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<Profile> update(
            @PathVariable Long id,
            @RequestParam("lastName") String lastName,
            @RequestParam("firstName") String firstName,
            @RequestParam("secondName") String secondName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("employmentNumber") String employmentNumber,
            @RequestParam("employmentStatus") String employmentStatus,
            @RequestParam("department") String department,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) throws IOException {
        
        Profile updatedProfile = new Profile();
        updatedProfile.setLastName(lastName);
        updatedProfile.setFirstName(firstName);
        updatedProfile.setSecondName(secondName);
        updatedProfile.setEmail(email);
        updatedProfile.setPhoneNumber(phoneNumber);
        updatedProfile.setEmploymentNumber(employmentNumber);
        updatedProfile.setEmploymentStatus(employmentStatus);
        updatedProfile.setDepartment(department);

        // Save file to the server if provided
        if (profilePicture != null && !profilePicture.isEmpty()) {
            String fileName = profilePicture.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, profilePicture.getBytes());
            updatedProfile.setProfilePicturePath(filePath.toString());
        }

        return new ResponseEntity<>(service.update(id, updatedProfile), HttpStatus.OK);
    }
}
