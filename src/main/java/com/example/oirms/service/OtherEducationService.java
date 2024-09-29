package com.example.oirms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.oirms.model.OtherEducation;
import com.example.oirms.repository.OtherEducationRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class OtherEducationService {

    @Autowired
    private OtherEducationRepository repository;

    public List<OtherEducation> getAll() {
        return repository.findAll();
    }

    public OtherEducation save(OtherEducation otherEducation) {
        return repository.save(otherEducation);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Update method accepting multiple parameters
    public OtherEducation update(Long id, String typeOfEducation, String institutionName,
                                 String registrationNumber, Integer startYear,
                                 Integer endYear, MultipartFile certificate) throws IOException {
        OtherEducation existingEducation = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Education record not found"));
        
        existingEducation.setTypeOfEducation(typeOfEducation);
        existingEducation.setInstitutionName(institutionName);
        existingEducation.setRegistrationNumber(registrationNumber);
        existingEducation.setStartYear(startYear);
        existingEducation.setEndYear(endYear);
        
        // If a new certificate is uploaded, save it and update the path
        if (certificate != null && !certificate.isEmpty()) {
            String fileName = certificate.getOriginalFilename();
            Path filePath = Path.of("uploads", fileName);
            Files.createDirectories(filePath.getParent()); // Ensure the directory exists
            Files.write(filePath, certificate.getBytes());
            existingEducation.setCertificatePath(filePath.toString());
        }

        return repository.save(existingEducation);
    }
}
