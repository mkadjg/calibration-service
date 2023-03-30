package com.calibration.service;

import com.calibration.dto.CalibrationConfirmationDto;
import com.calibration.dto.CalibrationForwardDto;
import com.calibration.dto.CalibrationSubmissionDto;
import com.calibration.model.Calibration;

public interface CalibrationService {
    Calibration create(CalibrationSubmissionDto dto);
    Calibration confirm(CalibrationConfirmationDto dto, Calibration calibration);
    Calibration forwardToTechnician(CalibrationForwardDto dto, Calibration calibration);
    Calibration confirmByTechnician(Calibration calibration);
    Calibration forwardToTypewriter(CalibrationForwardDto dto, Calibration calibration);
    Calibration confirmByTypewriter(Calibration calibration);
}
