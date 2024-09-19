package com.example.oirms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Experience createExperience(@RequestBody ExperienceDTO experienceDTO) {
        // Fetch user by ID instead of by username
        User user = userService.getUserById(experienceDTO.getUserId()); // Get user by ID
        
        Experience experience = new Experience();
        experience.setExperienceType(experienceDTO.getExperienceType());
        experience.setYearsOfExperience(experienceDTO.getYearsOfExperience());
        experience.setLocation(experienceDTO.getLocation());
        experience.setCertificateFilePath(experienceDTO.getCertificateFilePath());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setUser(user); // Set user

        return experienceService.saveExperience(experience);
    }

    @GetMapping("/{id}")
    public Experience getExperience(@PathVariable Long id) {
        return experienceService.getExperienceById(id);
    }
}
