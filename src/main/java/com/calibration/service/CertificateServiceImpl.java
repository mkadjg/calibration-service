package com.calibration.service;


import com.calibration.dto.CertificateCalibrationDto;
import com.calibration.model.Calibration;
import com.calibration.repository.CalibrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CertificateServiceImpl implements CertificateService {

    CalibrationRepository calibrationRepository;

    @Autowired
    CertificateServiceImpl(CalibrationRepository calibrationRepository) {
        this.calibrationRepository = calibrationRepository;
    }
    @Override
    public Calibration create(Calibration calibration, CertificateCalibrationDto dto) {
        return calibrationRepository.save(calibration.toBuilder()
                .certificateNumber(dto.getCertificateNumber())
                .calibrationMethod(dto.getCalibrationMethod())
                .calibrationLocation(dto.getCalibrationLocation())
                .issuanceDate(LocalDate.parse(dto.getIssuanceDate()))
                .standardName(dto.getStandardName())
                .standardType(dto.getStandardType())
                .standardSerialNumber(dto.getStandardSerialNumber())
                .standardTraceableToSI(dto.getStandardTraceableToSI()).build());
    }
}
