package com.calibration.controller;

import com.calibration.dto.PerformanceAssessmentDto;
import com.calibration.repository.PerformanceAssessmentRepository;
import com.calibration.service.PerformanceAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance-assessment")
public class PerformanceAssessmentController {

    PerformanceAssessmentRepository performanceAssessmentRepository;

    PerformanceAssessmentService performanceAssessmentService;

    PerformanceAssessmentController(PerformanceAssessmentRepository performanceAssessmentRepository,
                                    PerformanceAssessmentService performanceAssessmentService) {
        this.performanceAssessmentRepository = performanceAssessmentRepository;
        this.performanceAssessmentService = performanceAssessmentService;
    }

    @GetMapping("/find-by-calibration-id/{calibrationId}")
    public Object findAll(@PathVariable int calibrationId) {
        return performanceAssessmentRepository.findByCalibrationId(calibrationId);
    }

    @PostMapping("")
    public Object create(@RequestBody PerformanceAssessmentDto dto) {
        return ResponseEntity.status(201).body(performanceAssessmentService.create(dto));
    }


}
