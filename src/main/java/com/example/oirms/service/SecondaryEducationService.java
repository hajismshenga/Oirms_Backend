package com.example.oirms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.oirms.model.SecondaryEducation;
import com.example.oirms.repository.SecondaryEducationRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SecondaryEducationService {

    @Autowired
    private SecondaryEducationRepository repository;

    private static final String UPLOAD_DIR = "uploads/"; // Directory for file uploads

    public List<SecondaryEducation> getAll() {
        return repository.findAll();
    }

    public SecondaryEducation save(SecondaryEducation secondaryEducation) {
        return repository.save(secondaryEducation);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public SecondaryEducation update(Long id, String school, String district, String region,
                                     Integer startYear, Integer endYear, String indexNumber,
                                     MultipartFile certificate) throws IOException {
        SecondaryEducation existingEducation = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Education record not found"));

        existingEducation.setSchool(school);
        existingEducation.setDistrict(district);
        existingEducation.setRegion(region);
        existingEducation.setStartYear(startYear);
        existingEducation.setEndYear(endYear);
        existingEducation.setIndexNumber(indexNumber);

        // Handle file upload
        if (certificate != null && !certificate.isEmpty()) {
            String fileName = certificate.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, certificate.getBytes());
            existingEducation.setCertificatePath(filePath.toString());
        }

        return repository.save(existingEducation);
    }
}
