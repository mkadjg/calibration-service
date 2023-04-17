package com.calibration.controller;

import com.calibration.dto.PerformanceAssessmentDto;
import com.calibration.model.Calibration;
import com.calibration.repository.CalibrationRepository;
import com.calibration.repository.PerformanceAssessmentRepository;
import com.calibration.service.PerformanceAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/performance-assessment")
public class PerformanceAssessmentController {

    PerformanceAssessmentRepository performanceAssessmentRepository;

    PerformanceAssessmentService performanceAssessmentService;

    CalibrationRepository calibrationRepository;

    @Autowired
    PerformanceAssessmentController(PerformanceAssessmentRepository performanceAssessmentRepository,
                                    PerformanceAssessmentService performanceAssessmentService,
                                    CalibrationRepository calibrationRepository) {
        this.performanceAssessmentRepository = performanceAssessmentRepository;
        this.performanceAssessmentService = performanceAssessmentService;
        this.calibrationRepository = calibrationRepository;
    }

    @GetMapping("/find-by-calibration-id/{calibrationId}")
    public Object findAll(@PathVariable int calibrationId) {
        return performanceAssessmentRepository.findByCalibrationId(calibrationId);
    }

    @PostMapping("/{calibrationId}")
    public Object create(@RequestBody PerformanceAssessmentDto dto, @PathVariable int calibrationId) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(201).body(performanceAssessmentService.create(calibration, dto));
    }


}
