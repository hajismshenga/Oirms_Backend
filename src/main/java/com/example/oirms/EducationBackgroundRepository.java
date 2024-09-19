package com.example.oirms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationBackgroundRepository extends JpaRepository<EducationBackground, Long> {
    EducationBackground findByUser(User user);

    List<EducationBackground> findByUserId(Long id);
}
