package com.example.oirms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oirms.model.DemographicInfo;
import com.example.oirms.repository.DemographicInfoRepository;

import java.util.List;

@Service
public class DemographicInfoService {

    @Autowired
    private DemographicInfoRepository repository;

    // Fetch all demographic information
    public List<DemographicInfo> getAll() {
        return repository.findAll();
    }

    // Fetch a single demographic info by ID
    public DemographicInfo getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Demographic Info not found with id: " + id));
    }

    // Save new demographic info
    public DemographicInfo save(DemographicInfo demographicInfo) {
        return repository.save(demographicInfo);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    // Update existing demographic info
    public DemographicInfo update(Long id, DemographicInfo demographicInfo) {
        DemographicInfo existingInfo = repository.findById(id).orElseThrow(() -> new RuntimeException("Demographic Info not found"));
        existingInfo.setName(demographicInfo.getName());
        existingInfo.setDateOfBirth(demographicInfo.getDateOfBirth());
        existingInfo.setAddress(demographicInfo.getAddress());
        existingInfo.setNationality(demographicInfo.getNationality());
        existingInfo.setGender(demographicInfo.getGender());
        existingInfo.setPhoneNumber(demographicInfo.getPhoneNumber());
        existingInfo.setEmail(demographicInfo.getEmail());
        existingInfo.setBirthCertificatePath(demographicInfo.getBirthCertificatePath());
        return repository.save(existingInfo);
    }
}
