package com.calibration.service;

import com.calibration.model.Calibration;
import com.calibration.model.CalibrationRawData;
import com.calibration.repository.CalibrationRawDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CalibrationRawDataServiceImpl implements CalibrationRawDataService {

    CalibrationRawDataRepository calibrationRawDataRepository;

    @Autowired
    public CalibrationRawDataServiceImpl(CalibrationRawDataRepository calibrationRawDataRepository) {
        this.calibrationRawDataRepository = calibrationRawDataRepository;
    }

    @Override
    public void save(Calibration calibration, MultipartFile file) throws IOException {
        calibrationRawDataRepository.save(CalibrationRawData.builder()
                .document(file.getBytes())
                .calibration(calibration)
                .build());
    }
}
