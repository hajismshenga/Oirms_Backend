package com.example.oirms.repository;

import com.example.oirms.model.DemographicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemographicInfoRepository extends JpaRepository<DemographicInfo, Long> {
}
