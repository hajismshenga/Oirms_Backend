package com.example.oirms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oirms.model.PrimaryEducation;
import com.example.oirms.repository.PrimaryEducationRepository;

import java.util.List;

@Service
public class PrimaryEducationService {

    @Autowired
    private PrimaryEducationRepository repository;

    public List<PrimaryEducation> getAll() {
        return repository.findAll();
    }

    public PrimaryEducation save(PrimaryEducation primaryEducation) {
        return repository.save(primaryEducation);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public PrimaryEducation update(Long id, PrimaryEducation primaryEducation) {
        PrimaryEducation existingEducation = repository.findById(id).orElseThrow(() -> new RuntimeException("Education record not found"));
        existingEducation.setSchool(primaryEducation.getSchool());
        existingEducation.setDistrict(primaryEducation.getDistrict());
        existingEducation.setRegion(primaryEducation.getRegion());
        existingEducation.setStartYear(primaryEducation.getStartYear());
        existingEducation.setEndYear(primaryEducation.getEndYear());
        return repository.save(existingEducation);
    }
}
