package com.example.oirms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    List<Experience> findByUserId(Long id);
    // Custom query methods can be defined here
}
