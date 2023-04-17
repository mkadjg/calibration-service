package com.calibration.service;

import com.calibration.dto.PerformanceAssessmentDto;
import com.calibration.model.Calibration;
import com.calibration.model.PerformanceAssessment;
import com.calibration.repository.CalibrationRepository;
import com.calibration.repository.PerformanceAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceAssessmentServiceImpl implements PerformanceAssessmentService{

    PerformanceAssessmentRepository performanceAssessmentRepository;

    CalibrationRepository calibrationRepository;

    @Autowired
    PerformanceAssessmentServiceImpl(PerformanceAssessmentRepository performanceAssessmentRepository,
                                     CalibrationRepository calibrationRepository) {
        this.performanceAssessmentRepository = performanceAssessmentRepository;
        this.calibrationRepository = calibrationRepository;
    }

    @Override
    public PerformanceAssessment create(Calibration calibration, PerformanceAssessmentDto dto) {
        Calibration result = calibrationRepository.save(calibration.toBuilder()
                        .isAssessed(true)
                        .build());
        return performanceAssessmentRepository.save(PerformanceAssessment.builder()
                .performanceAssessmentNote(dto.getPerformanceAssessmentNote())
                .rating(dto.getRating())
                .calibration(result)
                .build());
    }
}
