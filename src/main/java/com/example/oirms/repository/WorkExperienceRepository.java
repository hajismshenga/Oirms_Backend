package com.example.oirms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.oirms.model.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
}
