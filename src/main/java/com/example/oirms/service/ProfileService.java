package com.example.oirms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oirms.model.Profile;
import com.example.oirms.repository.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public Optional<Profile> getProfile(Long id) {
        return repository.findById(id);
    }

    public Profile save(Profile profile) {
        return repository.save(profile);
    }

    public Profile update(Long id, Profile profile) {
        Profile existingProfile = repository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setFirstName(profile.getFirstName());
        existingProfile.setSecondName(profile.getSecondName());
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setPhoneNumber(profile.getPhoneNumber());
        existingProfile.setEmploymentNumber(profile.getEmploymentNumber());
        existingProfile.setEmploymentStatus(profile.getEmploymentStatus());
        existingProfile.setDepartment(profile.getDepartment());
        existingProfile.setProfilePicturePath(profile.getProfilePicturePath());
        return repository.save(existingProfile);
    }
}
