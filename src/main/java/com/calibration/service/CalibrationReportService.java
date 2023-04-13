package com.calibration.service;

import com.calibration.dto.CalibrationReportDto;
import com.calibration.model.Calibration;

public interface CalibrationReportService {
    Calibration create(Calibration calibration, CalibrationReportDto dto);
}
