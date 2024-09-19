package com.example.oirms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EducationBackgroundRepository educationRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Add method to get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserProfileDTO getUserProfile(String username) {
        User user = userRepository.findByUsername(username);
        List<EducationBackground> educationList = educationRepository.findByUserId(user.getId());
        List<Experience> experienceList = experienceRepository.findByUserId(user.getId());

        UserProfileDTO profileDTO = new UserProfileDTO();
        profileDTO.setUser(user);
        profileDTO.setEducationBackgrounds(educationList);
        profileDTO.setExperiences(experienceList);

        return profileDTO;
    }

    public byte[] generateUserProfileFile(String username) {
        // Generate file content (PDF/CSV) logic here
        return new byte[0];
    }
}
