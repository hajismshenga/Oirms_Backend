package com.example.oirms;

import java.util.List;

public class UserProfileDTO {
    private User user;
    private List<EducationBackground> educationBackgrounds;
    private List<Experience> experiences;

    // Getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<EducationBackground> getEducationBackgrounds() {
        return educationBackgrounds;
    }

    public void setEducationBackgrounds(List<EducationBackground> educationBackgrounds) {
        this.educationBackgrounds = educationBackgrounds;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }
}
