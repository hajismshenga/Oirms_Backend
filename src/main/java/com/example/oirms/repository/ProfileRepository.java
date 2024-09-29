package com.example.oirms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.oirms.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
