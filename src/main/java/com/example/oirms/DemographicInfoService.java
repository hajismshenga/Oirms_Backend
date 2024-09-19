package com.example.oirms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemographicInfoService {

    @Autowired
    private DemographicInfoRepository demographicInfoRepository;

    @Autowired
    private UserRepository userRepository;

    public DemographicInfo saveDemographicInfo(Long userId, DemographicInfo demographicInfo) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        demographicInfo.setUser(user);
        return demographicInfoRepository.save(demographicInfo);
    }

    public DemographicInfo getDemographicInfo(Long userId) {
        return demographicInfoRepository.findByUserId(userId);
    }
}
