package com.example.oirms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/education")
public class EducationBackgroundController {

    @Autowired
    private EducationBackgroundService educationBackgroundService;

    private final String uploadDir = "uploads/education/";

    @PostMapping("/{userId}")
    public ResponseEntity<EducationBackground> saveEducationBackground(
            @PathVariable Long userId,
            @RequestBody EducationBackground educationBackground,
            @RequestParam(value = "formFourChartFile", required = false) MultipartFile formFourChartFile,
            @RequestParam(value = "formSixChartFile", required = false) MultipartFile formSixChartFile,
            @RequestParam(value = "diplomaChartFile", required = false) MultipartFile diplomaChartFile,
            @RequestParam(value = "higherEducationChartFile", required = false) MultipartFile higherEducationChartFile) {
        
        // Handle file uploads (similar to your previous method)
        try {
            if (formFourChartFile != null) {
                String formFourChartFilePath = saveFile(formFourChartFile);
                educationBackground.setFormFourChartFilePath(formFourChartFilePath);
            }
            if (formSixChartFile != null) {
                String formSixChartFilePath = saveFile(formSixChartFile);
                educationBackground.setFormSixChartFilePath(formSixChartFilePath);
            }
            if (diplomaChartFile != null) {
                String diplomaChartFilePath = saveFile(diplomaChartFile);
                educationBackground.setDiplomaChartFilePath(diplomaChartFilePath);
            }
            if (higherEducationChartFile != null) {
                String higherEducationChartFilePath = saveFile(higherEducationChartFile);
                educationBackground.setHigherEducationChartFilePath(higherEducationChartFilePath);
            }

            // Save education background
            EducationBackground savedEducationBackground = educationBackgroundService.saveEducationBackground(userId, educationBackground);
            return ResponseEntity.ok(savedEducationBackground);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/regions")
    public ResponseEntity<Region[]> getRegions() {
        return ResponseEntity.ok(Region.values());
    }

    private String saveFile(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + fileName;
        java.nio.file.Path path = java.nio.file.Paths.get(filePath);
        java.nio.file.Files.write(path, file.getBytes());
        return filePath;
    }
}
