package com.example.oirms.repository;

import com.example.oirms.model.PrimaryEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryEducationRepository extends JpaRepository<PrimaryEducation, Long> {
}
