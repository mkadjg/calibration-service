package com.calibration.service;

import com.calibration.dto.PerformanceAssessmentDto;
import com.calibration.model.Calibration;
import com.calibration.model.PerformanceAssessment;

public interface PerformanceAssessmentService {
    PerformanceAssessment create(Calibration calibration, PerformanceAssessmentDto dto);
}
