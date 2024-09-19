package com.example.oirms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemographicInfoRepository extends JpaRepository<DemographicInfo, Long> {
    DemographicInfo findByUserId(Long userId);
}
