package com.example.oirms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.oirms.model.AdvancedEducation;
import com.example.oirms.repository.AdvancedEducationRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AdvancedEducationService {

    @Autowired
    private AdvancedEducationRepository repository;

    private static final String UPLOAD_DIR = "uploads/"; // Directory for file uploads

    public List<AdvancedEducation> getAll() {
        return repository.findAll();
    }

    public AdvancedEducation save(AdvancedEducation advancedEducation) {
        return repository.save(advancedEducation);
    }

    public AdvancedEducation update(Long id, String institutionName, String registrationNumber, String courseType,
                                     String courseName, Integer startYear, Integer endYear, MultipartFile certificate) throws IOException {
        AdvancedEducation existingEducation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education record not found"));
        
        existingEducation.setInstitutionName(institutionName);
        existingEducation.setRegistrationNumber(registrationNumber);
        existingEducation.setCourseType(courseType);
        existingEducation.setCourseName(courseName);
        existingEducation.setStartYear(startYear);
        existingEducation.setEndYear(endYear);

        // Handle file update if a new certificate is provided
        if (certificate != null && !certificate.isEmpty()) {
            String fileName = certificate.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, certificate.getBytes());
            existingEducation.setCertificatePath(filePath.toString());
        }

        return repository.save(existingEducation);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
