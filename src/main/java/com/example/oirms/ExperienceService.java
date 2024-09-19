package com.example.oirms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private UserRepository userRepository;

    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience getExperienceById(Long id) {
        return experienceRepository.findById(id).orElseThrow(() -> new RuntimeException("Experience not found with id: " + id));
    }

    public Experience addExperienceToUser(Long userId, Experience experience) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        experience.setUser(user);
        return experienceRepository.save(experience);
    }

    // Additional methods can be added as needed
}
