package com.example.oirms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oirms.model.WorkExperience;
import com.example.oirms.repository.WorkExperienceRepository;

import java.util.List;

@Service
public class WorkExperienceService {

    @Autowired
    private WorkExperienceRepository repository;

    public List<WorkExperience> getAll() {
        return repository.findAll();
    }

    public WorkExperience save(WorkExperience workExperience) {
        return repository.save(workExperience);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public WorkExperience update(Long id, WorkExperience workExperience) {
        WorkExperience existingExperience = repository.findById(id).orElseThrow(() -> new RuntimeException("Work experience not found"));
        existingExperience.setCompanyName(workExperience.getCompanyName());
        existingExperience.setPosition(workExperience.getPosition());
        existingExperience.setStartDate(workExperience.getStartDate());
        existingExperience.setEndDate(workExperience.getEndDate());
        return repository.save(existingExperience);
    }
}
