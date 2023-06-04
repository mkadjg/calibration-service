package com.calibration.service;

import com.calibration.model.Calibration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CalibrationRawDataService {
    public void save(Calibration calibration, MultipartFile file) throws IOException;
}
