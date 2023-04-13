package com.calibration.service;

import com.calibration.dto.CertificateCalibrationDto;
import com.calibration.model.Calibration;

public interface CertificateService {
    Calibration create(Calibration calibration, CertificateCalibrationDto dto);
}
