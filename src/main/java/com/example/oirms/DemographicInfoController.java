package com.example.oirms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demographics")
public class DemographicInfoController {

    @Autowired
    private DemographicInfoService demographicInfoService;

    @PostMapping("/{userId}")
    public ResponseEntity<DemographicInfo> saveDemographicInfo(@PathVariable Long userId, @RequestBody DemographicInfo demographicInfo) {
        DemographicInfo savedDemographicInfo = demographicInfoService.saveDemographicInfo(userId, demographicInfo);
        return ResponseEntity.ok(savedDemographicInfo);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DemographicInfo> getDemographicInfo(@PathVariable Long userId) {
        DemographicInfo demographicInfo = demographicInfoService.getDemographicInfo(userId);
        if (demographicInfo != null) {
            return ResponseEntity.ok(demographicInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
