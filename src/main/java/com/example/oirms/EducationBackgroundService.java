package com.example.oirms;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationBackgroundService {

    @Autowired
    private EducationBackgroundRepository educationBackgroundRepository;

    @Autowired
    private UserRepository userRepository;

    public EducationBackground saveEducationBackground(Long userId, EducationBackground educationBackground) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        educationBackground.setUser(user);
        return educationBackgroundRepository.save(educationBackground);
    }

    public Optional<EducationBackground> getEducationBackgroundByUserId(Long userId) {
        return educationBackgroundRepository.findById(userId);
    }
}
